package com.fourkites.ocean.es.writer.controller;

import com.fourkites.ocean.es.writer.config.properties.TrackingServiceProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@RequiredArgsConstructor
public class ThreadPoolConfig {
    @NonNull
    private BeanFactory beanFactory;

    @NonNull
    private TrackingServiceProperties trackingServiceProperties;

    @Bean
    public ExecutorService getAsyncExecutor() {
        ExecutorService executorService= Executors.newFixedThreadPool(trackingServiceProperties.getThreadSize(), new BasicThreadFactory.Builder()
                .namingPattern(trackingServiceProperties.getThreadNamePrefix())
                .build());
        return new TraceableExecutorService(beanFactory,executorService);
    }
}
