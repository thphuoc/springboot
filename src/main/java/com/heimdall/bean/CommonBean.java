package com.heimdall.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class CommonBean {
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
