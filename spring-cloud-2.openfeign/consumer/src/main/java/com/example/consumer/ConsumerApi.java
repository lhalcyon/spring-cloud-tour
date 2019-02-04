package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "consumer",url = "${baseUrl}")
public interface ConsumerApi {

    @RequestMapping(value = "/mock/delay",method = RequestMethod.GET)
    String getDelayMockData(@RequestParam("param") String param, @RequestParam("delay") String delay);

    @RequestMapping(value = "/mock/normal",method = RequestMethod.GET)
    String getMockData(@RequestParam("param") String param);
}
