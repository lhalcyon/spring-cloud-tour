package com.example.serviceregular;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegularController {

    @RequestMapping("/query")
    public String query(@RequestParam String param){
        return "regular service:"+param;
    }

    @RequestMapping("/after")
    public String after(@RequestParam String param){
        return "after:"+param;
    }
}
