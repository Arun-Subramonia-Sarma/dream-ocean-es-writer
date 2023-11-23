package com.fourkites.ocean.es.writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={ElasticsearchDataAutoConfiguration.class})
public class OceanEsWriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(OceanEsWriterApplication.class, args);
	}

}
