package com.fourkites.ocean.es.writer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private Long count;
    private Integer page;
    private AtomicInteger perPage=new AtomicInteger(0);
    private AtomicInteger groupsPerPage=new AtomicInteger(0);
    private Integer totalPages;
    private Integer totalGroups;
    private Integer totalPagesWoUnassigned;
    private Map<String, Integer> totalBucketSizes=new LinkedHashMap<>();

    public void incrementBucketLoadCount(String bucketName, int countOfLoads){
        totalBucketSizes.put(bucketName,countOfLoads);
    }
    public Integer incrementPerPageLoad(){
        return perPage.incrementAndGet();
    }

    public Integer incrementGroupsPerPage(){
        return groupsPerPage.incrementAndGet();
    }
}
