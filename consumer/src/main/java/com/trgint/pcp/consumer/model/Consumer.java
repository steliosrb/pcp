package com.trgint.pcp.consumer.model;

import com.trgint.pcp.consumer.helper.RandomHelper;

import java.util.UUID;

public class Consumer {

    public Consumer() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private ConsumerState state;

    public Boolean consumeTask(Task task) throws InterruptedException {
        // Task Consuming Simulation
        int sleepDuration = RandomHelper.randomIntBetween(300, 1000);
        Thread.sleep(sleepDuration);
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ConsumerState getState() {
        return state;
    }

    public void setState(ConsumerState state) {
        this.state = state;
    }


}
