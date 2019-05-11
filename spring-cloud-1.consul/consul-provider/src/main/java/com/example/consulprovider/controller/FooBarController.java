package com.example.consulprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class FooBarController {

    @Value("${foo.bar}")
    String fooBar;

    @RequestMapping("/foo")
    public String getFooBar(){
        return fooBar;
    }
}
