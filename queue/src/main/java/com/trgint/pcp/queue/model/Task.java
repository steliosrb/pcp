package com.trgint.pcp.queue.model;

import java.util.UUID;

public class Task {
    public Task() {
        super();
        this.id = UUID.randomUUID().toString();
    }

    public Task(String data) {
        super();
        this.id = UUID.randomUUID().toString();
    }

    private String id;
    private String data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
