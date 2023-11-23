package com.fourkites.ocean.es.writer.service.es;

import com.fourkites.ocean.es.writer.config.properties.VelocityTemplateProperties;
import com.fourkites.ocean.es.writer.model.TrackingRequest;
import com.fourkites.ocean.es.writer.model.enums.GroupingColumn;
import com.fourkites.ocean.es.writer.service.es.builder.ESTemplateQueryBuilder;
import com.fourkites.ocean.es.writer.suppliers.VelocityContextSupplier;
import com.fourkites.ocean.es.writer.suppliers.VelocityTemplateSupplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.script.mustache.SearchTemplateRequest;
import org.elasticsearch.script.mustache.SearchTemplateRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ESQueryBuilder {

    @NonNull
    private VelocityContextSupplier velocityContextSupplier;

    @NonNull
    private VelocityTemplateSupplier velocityTemplateSupplier;

    @NonNull
    private VelocityTemplateProperties velocityTemplateProperties;
    /*****
     * @param groupingColumnName
     * @return
     *
     * This method is used for fetching total number of loads that are available in each buckets returned
     */
    public CardinalityAggregationBuilder getCardinalityAggregationBuilder(String groupingColumnName){
        return AggregationBuilders.cardinality(groupingColumnName+"_total_count")
                .field("oceanInfo."+groupingColumnName+".keyword");
    }

    public TermsAggregationBuilder getTermsAggregationBuilder(String groupingColumnName, Integer totalSize, Integer loadsInGroup, Integer from, Integer pageSize, SortOrder sortOrder, String bucketSortField, String bucketSortOrder, boolean shouldLoadContent){
        TermsAggregationBuilder termsAggregationBuilder= AggregationBuilders.terms(groupingColumnName+"_group")
                .field("oceanInfo."+groupingColumnName+".keyword")
                .size(totalSize)
                .subAggregation(PipelineAggregatorBuilders.bucketSort("bucket_sort", List.of(new FieldSortBuilder("_key").order(sortOrder)))
                        .from(from)
                        .size(pageSize)

                );
        if(shouldLoadContent){
            termsAggregationBuilder.subAggregation(AggregationBuilders.topHits("load")
                    //.explain(true)
                    .size(loadsInGroup)
                    .sort(bucketSortField, SortOrder.fromString(bucketSortOrder))
            );
        }
        return termsAggregationBuilder ;
    }

    public QueryBuilder getQueryForGroup(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String groupColumnValue){

        BoolQueryBuilder loadQueryBuilder = ((BoolQueryBuilder) getFilterCondition(trackingRequest, companyName,null, null) )
                .must(QueryBuilders.matchQuery("oceanInfo."+groupingColumnName+".keyword",groupColumnValue));


        return loadQueryBuilder;
    }


    public BoolQueryBuilder getFilterCondition(TrackingRequest trackingRequest, String companyName, String groupingColumnName, String groupColumnValue){
         BoolQueryBuilder filterCondition= QueryBuilders.boolQuery().must(QueryBuilders.termQuery("shipper.id.keyword",companyName));
        if(groupingColumnName!=null && groupColumnValue!=null)
            filterCondition.mustNot(QueryBuilders.matchQuery("oceanInfo."+groupingColumnName+".keyword",groupColumnValue));
        return filterCondition;
    }

    public SearchTemplateRequest getSearchRequestForGroupingQuery(TrackingRequest request, String companyName, GroupingColumn groupingColumn, int pageStartIndex, int pageSize, int groupSize, String pageSortField, SortOrder pageSortOrder, String bucketSortField, String bucketSortOrder, int totalSize,  boolean shouldLoadContent, boolean loadContentInSameThread){
        return ESTemplateQueryBuilder.builder()
                .indexName("load")
                .templateName("grouped-search-template")
                .param("company-id",companyName)
                .param("grouping-column", groupingColumn.getGroupingColumn())
                .param("total-loads", totalSize)
                .param("page-from",pageStartIndex)
                .param("page-size",pageSize)
                .param("page-sort-field-name", pageSortField)
                .param("page-sort-order",pageSortOrder)
                .param("addBucketDetails",(shouldLoadContent && loadContentInSameThread))
                .param("bucket-size",groupSize)
                .param("bucket-sort-field-name",bucketSortField)
                .param("bucket-sort-order",bucketSortOrder)
                .build();
    }

    public SearchTemplateRequest getSearchRequestForSingleGroup(TrackingRequest request, String companyName, String groupingColumn,String groupingColumnValue, int loadsInGroup,String bucketSortField, String bucketSortOrder){
        return ESTemplateQueryBuilder.builder()
                .indexName("load")
                .templateName("grouped-search-template")
                .param("company-id",companyName)
                .param("grouping-column", groupingColumn)
                .param("grouping-column-value",groupingColumnValue)

                .param("bucket-size",loadsInGroup)
                .param("bucket-sort-field-name",bucketSortField)
                .param("bucket-sort-order",bucketSortOrder)
                .build();
    }

}
