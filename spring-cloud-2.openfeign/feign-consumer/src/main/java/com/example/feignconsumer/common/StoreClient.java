package com.example.feignconsumer.common;

import com.example.feignconsumer.common.conf.StoreClientConfiguration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "storeClient",url = "${feign.client.config.storeClient.url}",configuration = StoreClientConfiguration.class)
public interface StoreClient {

    @RequestMapping(method = RequestMethod.GET,value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST,value = "/stores/{storeId}",consumes = "application/json")
    Store update(@PathVariable("storeId") String storeId,Store store);

    @RequestMapping(method = RequestMethod.GET,value = "/storesWithDelay")
    List<Store> getStoresWithDelay(@RequestParam long delay);

}
