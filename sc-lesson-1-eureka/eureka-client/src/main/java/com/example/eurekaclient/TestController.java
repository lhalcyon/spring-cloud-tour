package com.example.eurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/test")
    public String test(@RequestParam(value = "name", defaultValue = "admin") String name) {
        return name +"test ,i am from port:" + port;
    }
}

