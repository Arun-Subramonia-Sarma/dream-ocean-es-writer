package com.fourkites.ocean.es.writer.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "es")
@Data
public class ElasticSearchProperties {
    private String host;
    private int port;
    private String schema;
}
