package com.fourkites.ocean.es.writer.service;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.model.GroupedResponse;
import com.fourkites.ocean.es.writer.model.Pagination;
import com.fourkites.ocean.es.writer.model.TrackingRequest;
import com.fourkites.ocean.es.writer.model.enums.GroupingColumn;
import com.fourkites.ocean.es.writer.service.es.ESQueryBuilder;
import com.fourkites.ocean.es.writer.service.es.ResponseHandler;
import com.fourkites.ocean.es.writer.service.processor.BucketProcessor;
import com.fourkites.ocean.es.writer.service.processor.LoadProcessor;
import com.fourkites.ocean.es.writer.utils.TimerUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.elasticsearch.script.mustache.SearchTemplateResponse;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoadService {

    @NonNull
    private ESQueryBuilder queryBuilder;

    @NonNull
    private ResponseHandler responseHandler;

    @NonNull
    private RestHighLevelClient elasticsearchClient;

    @NonNull
    private ExecutorService executorService;

    @TrackExecutionTime("Fetch all details - service ")
    public GroupedResponse getGroupByColumns(TrackingRequest request, String companyName, GroupingColumn groupingColumn, int currentPageNumber, int pageSize, int groupSize, String pageSortField, SortOrder pageSortOrder, String bucketSortField, String bucketSortOrder, boolean shouldLoadContent, boolean loadContentInSameThread) throws IOException, ExecutionException, InterruptedException {
        String groupingColumnName=groupingColumn.getGroupingColumn();
        int totalSize=pageSize*currentPageNumber*groupSize;
        int bucketLoadFrom=(currentPageNumber-1)*pageSize;

        // pagination.setGroupsPerPage(groupsPerPage);
        GroupedResponse groupedResponse=getDefaultGroupedResponse(currentPageNumber, groupingColumnName);
        Pagination pagination=groupedResponse.getPagination();

        CardinalityAggregationBuilder billingCountAggregationBuild = queryBuilder.getCardinalityAggregationBuilder(groupingColumnName);
        TermsAggregationBuilder billingAggregationBuild=queryBuilder.getTermsAggregationBuilder(groupingColumnName,totalSize,groupSize, bucketLoadFrom,pageSize,pageSortOrder, bucketSortField, bucketSortOrder, (shouldLoadContent && loadContentInSameThread) );
        QueryBuilder filterQuery=queryBuilder.getFilterCondition(request,companyName, groupingColumnName, "UnAssigned");

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder()
                .size(0)
                .query(filterQuery)
                .aggregation(billingCountAggregationBuild)
                .aggregation(billingAggregationBuild);


        SearchRequest searchRequest = new SearchRequest("load").source(searchSourceBuilder);
        log.info("[{}] The search request for grouping info with group content enabled ({}) on elastic search is {}","getGroupByColumns",(shouldLoadContent && loadContentInSameThread),searchRequest.source());
        SearchResponse response = elasticsearchClient.search(searchRequest, RequestOptions.DEFAULT);
        return handleResponse(request, companyName, response,pagination, groupingColumnName, pageSize, groupSize, groupedResponse, bucketSortField, bucketSortOrder,shouldLoadContent, loadContentInSameThread);
    }

    @TrackExecutionTime("Fetch all details using template - service ")
    public GroupedResponse getGroupByColumnsUsingESTemplate(TrackingRequest request, String companyName, GroupingColumn groupingColumn, int currentPageNumber, int pageSize, int groupSize, String pageSortField, SortOrder pageSortOrder, String bucketSortField, String bucketSortOrder, boolean shouldLoadContent, boolean loadContentInSameThread) throws IOException {
        String groupingColumnName=groupingColumn.getGroupingColumn();
        int totalSize=pageSize*currentPageNumber*groupSize;
        int bucketLoadFrom=(currentPageNumber-1)*pageSize;

        // pagination.setGroupsPerPage(groupsPerPage);
        GroupedResponse groupedResponse=getDefaultGroupedResponse(currentPageNumber, groupingColumnName);
        Pagination pagination=groupedResponse.getPagination();

        SearchTemplateRequest searchTemplateRequest = queryBuilder.getSearchRequestForGroupingQuery(request,companyName,groupingColumn,bucketLoadFrom,pageSize,groupSize,pageSortField, pageSortOrder,bucketSortField,bucketSortOrder,totalSize, shouldLoadContent,loadContentInSameThread);
        log.info("The script is {}",searchTemplateRequest.getScript());
        log.info("The search request is {}",searchTemplateRequest.getRequest().source());
        SearchTemplateResponse response = elasticsearchClient.searchTemplate(searchTemplateRequest, RequestOptions.DEFAULT);
        log.info("The template response is {}",response.getResponse().toString());
        return handleResponse(request, companyName, response.getResponse(),pagination, groupingColumnName,pageSize,groupSize, groupedResponse, bucketSortField,bucketSortOrder, shouldLoadContent,loadContentInSameThread);
    }
    public GroupedResponse handleResponse(TrackingRequest request, String companyName, SearchResponse response, Pagination pagination, String groupingColumnName,int groupsPerPage, int loadsInGroup, GroupedResponse groupedResponse,String bucketSortField, String bucketSortOrder, boolean shouldLoadContent, boolean loadContentInSameThread) throws IOException {
        TimerUtils timerUtils=TimerUtils.startTimer();
        Long totalLoads= responseHandler.getTotalLoads(response);
        log.info("[{}] Total loads matching the filter {} ","getGroupByColumns",totalLoads);
        pagination.setCount(totalLoads);
        Long totalGroups= responseHandler.getTotalGroups(response, groupingColumnName);
        pagination.setTotalGroups(totalGroups.intValue());
        pagination.setTotalPages((totalGroups.intValue()+groupsPerPage-1)/groupsPerPage);
        BucketProcessor bucketProcessor=new BucketProcessor(groupedResponse);
        LoadProcessor loadProcessor=new LoadProcessor(groupedResponse);
        responseHandler.processResponse(response,groupingColumnName, bucketProcessor, loadProcessor);
        timerUtils.endTimer();
        log.info("[{}] Time taken to fetch group information (along with load details - {}) is {}","getGroupByColumns",(shouldLoadContent && loadContentInSameThread),timerUtils.getExecutionTime());
        Long unassignedLoads=getLoadsInBucket(request, companyName, groupingColumnName, "UnAssigned");
        log.info("Total data in unassigned bucket is {}",unassignedLoads);
        int totalPagesWOUnassigned=pagination.getTotalPages();
        pagination.setTotalGroups(totalPagesWOUnassigned);
        if(shouldLoadContent && !loadContentInSameThread) {
            log.info("[{}] Initiating the search on elastic search for individual bucket in different thread since the shouldLoadContent is set to {} and loadContentInSameThread is set to {}","getGroupByColumns",shouldLoadContent,loadContentInSameThread);
            if(groupedResponse.getGroups()!=null) {
                Set<String> bucketNames = groupedResponse.getGroups().keySet();
                List<Future<?>> bucketsTaskFutres = new ArrayList<>();
                bucketNames.stream().forEach(currentBucketName -> {
                    bucketsTaskFutres.add(getLoadForBucketInThread(request, companyName, groupingColumnName, currentBucketName,0, loadsInGroup, bucketSortField,bucketSortOrder, loadProcessor));
                });
                waitForAllThreadsToComplete(bucketsTaskFutres);
            }
        }
        return groupedResponse;
    }
    public void waitForAllThreadsToComplete(List<Future<?>> bucketsTaskFutre){
        bucketsTaskFutre.stream().forEach(future->{
            try {
                future.get();
            } catch (InterruptedException e) {
                log.error("[[]] Exception occurred while waiting for the thread to get completed","waitForAllThreadsToComplete",e);
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                log.error("[[]] Exception occurred while waiting for the thread to get completed","waitForAllThreadsToComplete",e);
                throw new RuntimeException(e);
            }
        });
    }
    public GroupedResponse getDefaultGroupedResponse(Integer currentPageNumber, String groupingColumnName){
        GroupedResponse groupedResponse=new GroupedResponse();
        Pagination pagination=new Pagination();
        pagination.setPage(currentPageNumber);
        groupedResponse.setPagination(pagination);
        groupedResponse.setGroupBy(groupingColumnName);
        return groupedResponse;
    }

    public Future<?> getLoadForBucketInThread(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String groupingColumnValue, int loadStartFrom, int bucketSize, String sortField, String sortOrder, LoadProcessor loadProcessor){
        return executorService.submit(()->{
            try {
                getLoadsForBucket(trackingRequest, companyName, groupingColumnName, groupingColumnValue, loadStartFrom, bucketSize, sortField, sortOrder, loadProcessor );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void getLoadsForBucket(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String groupingColumnValue,int loadStartFrom, int bucketSize, String sortField, String sortOrder, LoadProcessor loadProcessor) throws IOException {
        TimerUtils timerUtils = TimerUtils.startTimer();
//        QueryBuilder loadQueryBuilder= queryBuilder.getQueryForGroup(trackingRequest, companyName, groupingColumnName,bucketName);
//        if(bucketName!=null && bucketName.equalsIgnoreCase("UnAssigned"))
//            bucketSize=50;
//        SearchSourceBuilder loadSourceBuilder=new SearchSourceBuilder()
//                .query(loadQueryBuilder)
//                .from(loadStartFrom)
//                .size(bucketSize)
//                .sort(SortBuilders.fieldSort(sortField).order(SortOrder.fromString(sortOrder)));
//
//        log.info("[{}] The query to fetch the load information from elastic search for bucket {} is {}","getLoadsForBucket",bucketName, loadSourceBuilder);
//        SearchRequest searchRequest = new SearchRequest("load").source(loadSourceBuilder);
        SearchTemplateRequest searchTemplateRequest = queryBuilder.getSearchRequestForSingleGroup(trackingRequest,companyName,groupingColumnName,groupingColumnValue,bucketSize, sortField,sortOrder);
        SearchResponse response = elasticsearchClient.search(searchTemplateRequest.getRequest(), RequestOptions.DEFAULT);
        responseHandler.processResponse(response, groupingColumnValue,loadProcessor );
        timerUtils.endTimer();
        log.info("[{}] Time taken to fetch loads for a single bucket with column {}={} is {} ms","getLoadsForBucket",groupingColumnName, groupingColumnValue, timerUtils.getExecutionTime());
    }

    public Future<Long> getLoadsInBucketInThread(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String bucketName){
        return executorService.submit(()->{
            return getLoadsInBucket(trackingRequest, companyName, groupingColumnName, bucketName );
        });
    }

    public Long getLoadsInBucket(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String bucketName) throws IOException {
        TimerUtils timerUtils = TimerUtils.startTimer();
        QueryBuilder loadQueryBuilder= queryBuilder.getQueryForGroup(trackingRequest, companyName, groupingColumnName,bucketName);
        SearchSourceBuilder loadSourceBuilder=new SearchSourceBuilder()
                .query(loadQueryBuilder);
        CountRequest countRequest=new CountRequest().indices("load").source(loadSourceBuilder);
        CountResponse response=elasticsearchClient.count(countRequest, RequestOptions.DEFAULT);
        return response.getCount();
    }


}
