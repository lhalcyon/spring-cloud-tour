package com.example.consulconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "consul-provider")
public interface ClientFeign {

    @RequestMapping(value = "/hi")
    String sayHiFromConsumer(@RequestParam(value = "name") String name);
}

