package com.codeclub.subject.application.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public FeignRequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
