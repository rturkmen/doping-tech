package com.doping.tech.config;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class DatabaseInitializer implements BeforeAllCallback, AfterAllCallback {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;


    public void executeSqlScript(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        String sql = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        jdbcTemplate.execute(sql);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
       // executeSqlScript("classpath:sql/data-revert.sql");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
   //     executeSqlScript("classpath:sql/data.sql");
    }
}
