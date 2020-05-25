package com.trgint.pcp.consumer.controller;

import com.trgint.pcp.consumer.service.IConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessagingController {

    @Autowired
    private IConsumerService consumerService;

    @GetMapping(path = "/consumer", produces = "application/json")
    public ResponseEntity<String> consume() {
        if (consumerService.addConsumer())
            return new ResponseEntity<>("", HttpStatus.OK);
        else
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/wake-up", produces = "application/json")
    public ResponseEntity<String> wakeUp() {
        consumerService.notifyConsumers();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
