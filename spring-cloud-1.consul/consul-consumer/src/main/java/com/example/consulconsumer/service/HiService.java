package com.example.consulconsumer.service;

import com.example.consulconsumer.ClientFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
public class HiService {

    @Autowired
    ClientFeign clientFeign;


    public String sayHi(String name) {
        return clientFeign.sayHiFromConsumer(name);
    }
}
