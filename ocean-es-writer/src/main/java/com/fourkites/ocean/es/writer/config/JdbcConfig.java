package com.fourkites.ocean.es.writer.config;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcConfig {

    @NonNull
    private DataSource dataSource;

    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

}
