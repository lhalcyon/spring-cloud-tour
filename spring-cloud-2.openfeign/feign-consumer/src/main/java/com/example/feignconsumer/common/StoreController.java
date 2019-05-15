package com.example.feignconsumer.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

    private final
    StoreClient storeClient;

    @Autowired
    public StoreController(StoreClient storeClient) {
        this.storeClient = storeClient;
    }

    @RequestMapping("/stores")
    public List<Store> getStores(){
        return storeClient.getStores();
    }

    @RequestMapping("/stores/{storeId}")
    public Store update(@PathVariable String storeId,Store store){
        return storeClient.update(storeId,store);
    }

    @RequestMapping("/storesWithDelay")
    public List<Store> getStoresWithDelay(@RequestParam long delay){
        return storeClient.getStoresWithDelay(delay);
    }
}
