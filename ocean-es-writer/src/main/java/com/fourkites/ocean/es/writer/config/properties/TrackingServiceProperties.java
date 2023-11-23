package com.fourkites.ocean.es.writer.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="tracking")
@Data
public class TrackingServiceProperties {
    private String baseUrl;
    private String trackingPath;
    private int threadSize;
    private String threadNamePrefix;
    private String clientId;
    private String trackingSecret;
    private int pageSize;
}
