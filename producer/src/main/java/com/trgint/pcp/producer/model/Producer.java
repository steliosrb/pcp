package com.trgint.pcp.producer.model;

import com.trgint.pcp.producer.helper.RandomHelper;

import java.util.UUID;

public class Producer {

    public Producer() {
        super();
        this.id = UUID.randomUUID().toString();
        this.lastTask = null;
        this.state = ProducerState.IDLE;
    }

    private String id;
    private Task lastTask;
    private ProducerState state;

    public Task produceTask() throws InterruptedException{
        if(lastTask!=null)
            return lastTask;
        else {
            // Task Creation Simulation
            int sleepDuration = RandomHelper.randomIntBetween(100,300);
            Thread.sleep(sleepDuration);
            lastTask = new Task("data");
            return lastTask;
        }
    }
    public void resetLastTask(){
        lastTask = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProducerState getState() {
        return state;
    }

    public void setState(ProducerState state) {
        this.state = state;
    }



}
