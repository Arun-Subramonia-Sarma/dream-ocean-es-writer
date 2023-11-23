package com.fourkites.ocean.es.writer.config;


import com.amazonaws.auth.AWS4Signer;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.fourkites.ocean.es.writer.config.properties.ElasticSearchProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@Configuration
@RequiredArgsConstructor
public class EsConfig {

    @NonNull
    private ElasticSearchProperties elasticSearchProperties;

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        AWS4Signer signer = new AWS4Signer();
        signer.setServiceName("es");
        signer.setRegionName("us-east-1");

        AWSCredentialsProvider credentialsProvider = new DefaultAWSCredentialsProviderChain();
        HttpRequestInterceptor interceptor =
                new AWSRequestSigningApacheInterceptor("es", signer, credentialsProvider);
        //TODO: This is to be tested
        HttpHost httpHost =
                new HttpHost(
                        elasticSearchProperties.getHost(),
                        elasticSearchProperties.getPort(),
                        elasticSearchProperties.getSchema());
        return new RestHighLevelClient(
                RestClient.builder(httpHost)
   //                     .setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor))
        );
    }
    @Bean
    public ElasticsearchRestTemplate elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }
//    @Bean
//    public ClientConfiguration elasticClientConfiguration(){
//        return ClientConfiguration.builder()
//                .connectedTo("vpc-dynamic-yard-qat-rfh22a4oc2wbznsy4q4byitir4.us-east-1.es.amazonaws.com:9300")
//                //.connectedTo("localhost:9200")
//                //.usingSsl()
//                //.withBasicAuth("elastic","x5WschgLxf4vSHwk")
//                .build();
//    }
//    @Bean
//    public RestHighLevelClient elasticsearchClient() {
//
//
//        return RestClients.create(clientConfiguration)
//                .rest();
////        return new RestHighLevelClient(RestClient.builder(HttpHost.create(endpoint)));
//    }

}
