package com.example.server.controller;


import com.example.server.domain.Book;
import com.example.server.service.CircuitbreakerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/circuitbreaker")
@RestController
public class CircuitbreakerController {

    private static Logger logger = LoggerFactory.getLogger(CircuitbreakerController.class);

    private final CircuitbreakerService circuitbreakerService;

    @Autowired
    public CircuitbreakerController(CircuitbreakerService circuitbreakerService) {
        this.circuitbreakerService = circuitbreakerService;
    }

    @RequestMapping(value = "/queryBook",method = RequestMethod.GET)
    public ResponseEntity<Book> queryBook(@RequestParam(value = "param",defaultValue = "") String param) throws Exception{
        logger.info("begin to query book");
        return new ResponseEntity<>(circuitbreakerService.queryBook(param), HttpStatus.OK);
    }

}
