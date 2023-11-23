package com.fourkites.ocean.es.writer.service.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourkites.ocean.es.writer.model.GroupedResponse;
import com.fourkites.ocean.es.writer.model.Load;
import com.fourkites.ocean.es.writer.model.Pagination;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.SearchHit;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Getter
public class LoadProcessor {
    @NonNull
    private GroupedResponse groupedResponse;


    public Map<String, List<Load>> processLoad(SearchHit[] searchHits, String bucketName){
        Pagination pagination=groupedResponse.getPagination();
        long totalDocInBucket=searchHits.length;
        for(SearchHit searchHit:searchHits){
            // log.info("The billing name is {} and total number of loads are {} and the loads are {}",currentBookingNumber, totalDocInBucket, searchHit.getSourceAsString());
            ObjectMapper objectMapper=new ObjectMapper();
            try {
                Load load=objectMapper.readValue(searchHit.getSourceAsString(), Load.class);
                groupedResponse.addGroup(bucketName, load);
                pagination.incrementPerPageLoad();

            } catch (JsonProcessingException e) {
                log.error("Exception while converting into Java Objects {} and the original message {}",e.getMessage(),e.getOriginalMessage());
                log.error("Message {}",searchHit.getSourceAsString());
            }
        }
        return groupedResponse.getGroups();
    }

}
