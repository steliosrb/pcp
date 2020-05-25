package com.trgint.pcp.queue.controller;


import com.trgint.pcp.queue.model.Task;
import com.trgint.pcp.queue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessagingController {

    @Autowired
    QueueService queueService;

    @GetMapping(path = "/queue/is-full", produces = "application/json")
    public Boolean isQueueFull() {

        return queueService.isQueueFull();
    }

    @GetMapping(path = "/queue/is-empty", produces = "application/json")
    public Boolean isQueueEmpty() {

        return queueService.isQueueEmpty();
    }

    @PostMapping(path = "/queue/task", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addToQueue(@RequestBody Task task) {
        if (queueService.addToQueue(task))
            return new ResponseEntity<>("", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(path = "queue/task", produces = "application/json")
    public ResponseEntity<Task> getFromQueue() {
        return new ResponseEntity<>(queueService.takeFromQueue(), HttpStatus.OK);
    }


}
