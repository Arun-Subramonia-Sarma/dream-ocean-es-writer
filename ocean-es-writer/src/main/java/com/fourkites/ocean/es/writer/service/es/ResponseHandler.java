package com.fourkites.ocean.es.writer.service.es;

import com.fourkites.ocean.es.writer.service.processor.BucketProcessor;
import com.fourkites.ocean.es.writer.service.processor.LoadProcessor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.metrics.cardinality.ParsedCardinality;
import org.elasticsearch.search.aggregations.metrics.tophits.ParsedTopHits;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ResponseHandler {

    public Long getTotalLoads(SearchResponse response){
        if(response.getHits()!=null)
            return response.getHits().getTotalHits();
        log.warn("Response total load hit is empty");
        return 0l;
    }

    public Long getTotalGroups(SearchResponse response, String groupColumName){
        ParsedCardinality cardinalityAggregator = (ParsedCardinality)response.getAggregations().get(groupColumName+"_total_count");
        if(cardinalityAggregator!=null){
            return cardinalityAggregator.getValue();
        }
        log.error("The group total is not set in the elastic search response. The value is expected with in the field {}_total_count",groupColumName);
        return 0L;
    }

    public void processResponse(SearchResponse response, String groupColumName, BucketProcessor bucketProcessor, LoadProcessor loadProcessor){
        ParsedStringTerms parsedStringTerms=(ParsedStringTerms)response.getAggregations().get(groupColumName+"_group");
        parsedStringTerms.getBuckets().stream()
            .forEach(bucket-> {
                String bucketName = bucket.getKeyAsString();
                Long bucketCount = bucket.getDocCount();
                bucketProcessor.processBucket(bucketName, bucketCount);
                List<Aggregation> topHits=bucket.getAggregations().asList();
                topHits.stream()
                    .forEach(hit-> {
                        SearchHit searchHits[] =((ParsedTopHits)hit).getHits().getHits();
                        loadProcessor.processLoad(searchHits,bucketName);
                    }
                );
            }
        );
    }

    public void processResponse(SearchResponse response, String bucketName, LoadProcessor loadProcessor){
        SearchHit searchHits[] =response.getHits().getHits();
        loadProcessor.processLoad(searchHits, bucketName);
    }

}
