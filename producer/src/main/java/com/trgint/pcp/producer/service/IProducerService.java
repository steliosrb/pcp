package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Producer;

public interface IProducerService {
    boolean addProducer();
    void produce(Producer producer) throws InterruptedException;
    void notifyProducers();
}
