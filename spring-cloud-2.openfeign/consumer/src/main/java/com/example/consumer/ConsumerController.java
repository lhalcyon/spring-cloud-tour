package com.example.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/mock")
public class ConsumerController {

    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Value("${baseUrl}")
    private String baseUrl;

    private final ConsumerApi api;

    @Autowired
    public ConsumerController(ConsumerApi api) {
        this.api = api;
    }

    @PostConstruct
    public void initApi(){
        logger.info("baseUrl = {}",baseUrl);
    }

    @RequestMapping(value = "/normal",method = RequestMethod.GET)
    public String normalRequest(@RequestParam(value = "param",defaultValue = "") String param){
       return api.getMockData(param);
    }

    @RequestMapping(value = "/delay",method = RequestMethod.GET)
    public String delayRequest(@RequestParam(value = "param",defaultValue = "") String param,
                               @RequestParam(value = "delay",defaultValue = "2000") String delay){
        return api.getDelayMockData(param,delay);
    }
}
