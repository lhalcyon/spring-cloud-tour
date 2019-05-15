package com.example.feignprovider;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreController {

    private List<Store> temp = new ArrayList<Store>(){
        {
            add(new Store(0,"Arthas"));
            add(new Store(1,"Jeona"));
            add(new Store(2,"Uther"));
        }
    };

    @RequestMapping("/stores")
    public List<Store> getStores(){
        return temp;
    }

    @RequestMapping("/stores/{storeId}")
    public Store update(@PathVariable String storeId,Store store){
        int index = temp.indexOf(store);
        if (index == -1){
            temp.add(store);
            return null;
        } else {
            temp.set(index,store);
            return store;
        }
    }

    @RequestMapping("/storesWithDelay")
    public List<Store> getStoresWithDelay(@RequestParam long delay){
        try {
            Thread.sleep(delay);
            return getStores();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
