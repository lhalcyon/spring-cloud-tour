package com.example.feignconsumer.common.conf;

import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;

import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;

public class StoreClientConfiguration {

    @Bean
    public RequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "admin", Charset.forName("utf-8"));
    }



}
