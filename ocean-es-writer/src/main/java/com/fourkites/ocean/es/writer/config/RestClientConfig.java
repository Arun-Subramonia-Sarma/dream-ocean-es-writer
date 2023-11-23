package com.fourkites.ocean.es.writer.config;


import com.fourkites.ocean.es.writer.config.properties.AuthProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    @NonNull
    private AuthProperties authProperties;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
        //return new RestTemplateBuilder().basicAuthentication(authProperties.getUser(), authProperties.getPassword()).build();
        //return new RestTemplate(getClientHttpRequestFactory());
    }

//    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory()
//    {
//        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
//                = new HttpComponentsClientHttpRequestFactory();
//
//        clientHttpRequestFactory.setHttpClient(httpClient());
//
//        return clientHttpRequestFactory;
//    }
//
//    private HttpClient httpClient()
//    {
//        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//
//        credentialsProvider.setCredentials(AuthScope.ANY,
//                new UsernamePasswordCredentials("arun.sarma@fourkites.com", "_Whoareyou3ask"));
//
//        HttpClient client = HttpClientBuilder
//                .create()
//                .setDefaultCredentialsProvider(credentialsProvider)
//                .build();
//        return client;
//    }
}
