package com.fourkites.ocean.es.writer.controller;

import com.fourkites.ocean.es.writer.annotation.TrackExecutionTime;
import com.fourkites.ocean.es.writer.model.GroupedResponse;
import com.fourkites.ocean.es.writer.model.TrackingRequest;
import com.fourkites.ocean.es.writer.model.enums.GroupingColumn;
import com.fourkites.ocean.es.writer.service.LoadService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("oceanservice/api/v2/loads")
@RequiredArgsConstructor
@Slf4j
public class LoadController {

    @NonNull
    private LoadService loadService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found"),
            @ApiResponse(responseCode = "400", description = "Parameters mismatch"),
            @ApiResponse(responseCode = "403", description = "User not authorized to perform this operation"),
            @ApiResponse(responseCode = "401", description = "No auth token found in the request")
    })
    @Parameters(value={
            @Parameter(name="groupBy",  required = true, description="The grouping column"),
            @Parameter(name="company_id", required = true, description = "Tenant"),
            @Parameter(name="page", required = true, description = "The page number to be returned by the Ocean Service. This is different from the page of the Tracking service in the sense, the param in the tracing service will be determine the page number to be returned when tracking service is called to return the loads"),
            @Parameter(name="page_size", required = true, description = "The total number of groups to be returned from the Ocean Service. This is different from the per_page of the Tracking service in the sense, the param in the tracing service will be determine the number of load(tracking info) to be returned by the tracking service"),
            @Parameter(name="loads_per_group", required = false, description = "Max number of loads within each group"),
            @Parameter(name="sort_order", required = false, description = "Order order of the group")
    })
    @PostMapping(value="grouped",consumes="application/json", produces = "application/json")
    @TrackExecutionTime("Service completion")
    public GroupedResponse getResponseFromTracking(
            @RequestBody TrackingRequest request,
            @RequestParam(value="groupBy", required = true) GroupingColumn groupByColumn,
            @RequestParam(value="company_id", required = true) String companyId,
            @RequestParam(value="page", required = true) Integer currentPageNumber,
            @RequestParam(value="page_size", required = true, defaultValue = "10")Integer pageSize,
            @RequestParam(value="loads_per_group", required = false, defaultValue = "10") Integer bucketSize,
            @RequestParam(value="page_sort_field", required = false, defaultValue = "_key") String pageSortField,
            @RequestParam(value="sort_order", required = false, defaultValue = "ASC") String pageSortOrder,
            @RequestParam(value="bucket_sort_field", required=false, defaultValue="createdAt") String bucketSortField,
            @RequestParam(value="bucket_sort_order", required = false, defaultValue ="desc") String bucketSortOrder,
            @RequestParam(value="should_load_content", required=false, defaultValue="true") boolean shouldLoadContent,
            @RequestParam(value="content_in_same_thread", required=false, defaultValue = "false") boolean loadContentInSameThread) throws InterruptedException, IOException, ExecutionException {
        log.debug("[{}] groupBy {}","getResponseFromTracking",groupByColumn);
        log.debug("[{}] company_id {}","getResponseFromTracking",companyId);
        log.debug("[{}] page {}","getResponseFromTracking",currentPageNumber);
        log.debug("[{}] page_size {}","getResponseFromTracking",pageSize);
        log.debug("[{}] loads_per_group {}","getResponseFromTracking",bucketSize);
        log.debug("[{}] sort_order {}","getResponseFromTracking",pageSortOrder);
        log.debug("[{}] should_load_content {}","getResponseFromTracking",shouldLoadContent);
        log.debug("[{}] content_in_same_thread {}","getResponseFromTracking",loadContentInSameThread);
        return loadService.getGroupByColumns(request, companyId, groupByColumn,currentPageNumber, pageSize, bucketSize, pageSortField, SortOrder.fromString(pageSortOrder),bucketSortField, bucketSortOrder, shouldLoadContent,loadContentInSameThread);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The product was not found"),
            @ApiResponse(responseCode = "400", description = "Parameters mismatch"),
            @ApiResponse(responseCode = "403", description = "User not authorized to perform this operation"),
            @ApiResponse(responseCode = "401", description = "No auth token found in the request")
    })
    @Parameters(value={
            @Parameter(name="groupBy",  required = true, description="The grouping column"),
            @Parameter(name="company_id", required = true, description = "Tenant"),
            @Parameter(name="page", required = true, description = "The page number to be returned by the Ocean Service. This is different from the page of the Tracking service in the sense, the param in the tracing service will be determine the page number to be returned when tracking service is called to return the loads"),
            @Parameter(name="page_size", required = true, description = "The total number of groups to be returned from the Ocean Service. This is different from the per_page of the Tracking service in the sense, the param in the tracing service will be determine the number of load(tracking info) to be returned by the tracking service"),
            @Parameter(name="loads_per_group", required = false, description = "Max number of loads within each group"),
            @Parameter(name="sort_order", required = false, description = "Order order of the group")
    })
    @PostMapping(value="template",consumes="application/json", produces = "application/json")
    @TrackExecutionTime("Service completion")
    public GroupedResponse getTemplateResponse(
            @RequestBody TrackingRequest request,
            @RequestParam(value="groupBy", required = true) GroupingColumn groupByColumn,
            @RequestParam(value="company_id", required = true) String companyId,
            @RequestParam(value="page", required = true) Integer currentPageNumber,
            @RequestParam(value="page_size", required = true, defaultValue = "10")Integer pageSize,
            @RequestParam(value="loads_per_group", required = false, defaultValue = "10") Integer bucketSize,
            @RequestParam(value="page_sort_field", required = false, defaultValue = "_key") String pageSortField,
            @RequestParam(value="sort_order", required = false, defaultValue = "ASC") String pageSortOrder,
            @RequestParam(value="bucket_sort_field", required=false, defaultValue="createdAt") String bucketSortField,
            @RequestParam(value="bucket_sort_order", required = false, defaultValue ="desc") String bucketSortOrder,
            @RequestParam(value="should_load_content", required=false, defaultValue="true") boolean shouldLoadContent,
            @RequestParam(value="content_in_same_thread", required=false, defaultValue = "false") boolean loadContentInSameThread) throws IOException {
        return loadService.getGroupByColumnsUsingESTemplate(request, companyId, groupByColumn,currentPageNumber, pageSize, bucketSize,pageSortField, SortOrder.fromString(pageSortOrder),bucketSortField, bucketSortOrder, shouldLoadContent,loadContentInSameThread);
    }
}
