package com.trgint.pcp.producer.controller;

import com.trgint.pcp.producer.service.IProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessagingController {

    @Autowired
    private IProducerService producerService;

    @GetMapping(path = "/producer", produces = "application/json")
    public ResponseEntity<String> produce() {
        if (producerService.addProducer())
            return new ResponseEntity<>("", HttpStatus.OK);
        else
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/wake-up", produces = "application/json")
    public ResponseEntity<String> wakeUp() {
        producerService.notifyProducers();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
