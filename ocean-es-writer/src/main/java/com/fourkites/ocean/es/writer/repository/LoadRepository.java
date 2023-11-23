package com.fourkites.ocean.es.writer.repository;

import com.fourkites.ocean.es.writer.model.Load;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LoadRepository extends ElasticsearchRepository<Load, String> {
}
