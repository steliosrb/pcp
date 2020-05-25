package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Consumer;

public interface IConsumerService {
    boolean addConsumer();
    void consume(Consumer consumer) throws InterruptedException;
    void notifyConsumers();
}
