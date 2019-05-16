package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final
    GreetingClient greetingClient;

    @Autowired
    public GreetingController(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @RequestMapping("/greeting")
    public String getGreeting(){
        return greetingClient.getGreeting("Jack");
    }

}
