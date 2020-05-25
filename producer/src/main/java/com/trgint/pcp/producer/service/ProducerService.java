package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Producer;
import com.trgint.pcp.producer.model.ProducerState;
import com.trgint.pcp.producer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
@Scope("singleton")
public class ProducerService implements IProducerService {
    @Autowired
    private IQueueAPI queueAPI;
    @Autowired
    private IConsumerAPI consumerAPI;
    private Queue<Producer> producersList = new LinkedList<>();
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void produce(Producer producer) throws InterruptedException {
        producer.setState(ProducerState.BUSY);
        while (true) {
            Task task = producer.produceTask();
            while (queueAPI.apiCallIsFull()) {
                producer.setState(ProducerState.IDLE);
                return;
            }

            if (queueAPI.apiCallAdd(task)) {
                System.out.println("Producer " + producer.getId() + " added task " + task.getId());
                producer.resetLastTask();
                //notify consumers
                boolean isNotified;
                do {
                    isNotified = consumerAPI.apiCallNotify();
                    if (!isNotified) {
                        System.out.println("Consumer not notified, retrying...");
                        Thread.sleep(1000);
                    }
                } while (!isNotified);
            }
        }
    }

    private void produceOnBackground(Producer producer) {
        taskExecutor.execute(() -> {
            try {
                produce(producer);
            } catch (Exception e) {

            }
        });
    }

    @Override
    public boolean addProducer() {
        Producer producer = new Producer();
        produceOnBackground(producer);
        return producersList.add(producer);
    }

    @Override
    public void notifyProducers() {
        System.out.println("Producers notified");
        producersList.stream()
                .filter(producer -> producer.getState() == ProducerState.IDLE)
                .forEach(this::produceOnBackground);
    }
}
