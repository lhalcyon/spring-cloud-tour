package com.example.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mock")
public class MockController {

    @RequestMapping("/normal")
    public String normalRequest(@RequestParam(value = "param",defaultValue = "") String param){
        return "hello"+param;
    }

    @RequestMapping("/delay")
    public String delayRequest(@RequestParam(value = "param",defaultValue = "") String param,
                               @RequestParam(value = "delay",defaultValue = "2000") String delay){
        int delayMilli = Integer.parseInt(delay);
        try {
            Thread.sleep(delayMilli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello"+param;
    }
}
