package com.fourkites.ocean.es.writer.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="application.client")
@Data
public class AuthProperties {
    private String user;
    private String password;
}
