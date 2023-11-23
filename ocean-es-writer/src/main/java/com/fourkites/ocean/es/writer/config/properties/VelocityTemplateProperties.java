package com.fourkites.ocean.es.writer.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "velocity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VelocityTemplateProperties {
    private String groupTemplate;
    private String loadInGroupTemplate;
    private String singleGroupCount;
    private String filterTemplate;

}
