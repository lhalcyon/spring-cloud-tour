package com.example.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicInfoController {

    @Value("${server.port}")
    Integer port;

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("/greeting")
    public String getGreeting(String name){
        String theName = name == null ? "there" : name;
        return "Hi," + theName +". " + applicationName +":"+port +" send their regards";
    }

}
