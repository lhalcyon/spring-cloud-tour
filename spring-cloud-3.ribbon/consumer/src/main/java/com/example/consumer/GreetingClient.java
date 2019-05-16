package com.example.consumer;

import com.example.consumer.conf.GreetingClientConfiguration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "lh-provider",configuration = GreetingClientConfiguration.class)
public interface GreetingClient {

    @RequestMapping("/greeting")
    String getGreeting(@RequestParam String name);
}
