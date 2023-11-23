package com.fourkites.ocean.es.writer.service.processor;

import com.fourkites.ocean.es.writer.model.GroupedResponse;
import com.fourkites.ocean.es.writer.model.Pagination;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BucketProcessor {

    @NonNull
    private GroupedResponse groupedResponse;


    public void processBucket(String bucketName, Long totalDocumentsInBucket){
        Pagination pagination=groupedResponse.getPagination();
        pagination.incrementGroupsPerPage();
        pagination.incrementBucketLoadCount(bucketName,totalDocumentsInBucket.intValue());
        groupedResponse.addGroup(bucketName);
    }
}
