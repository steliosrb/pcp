package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Consumer;
import com.trgint.pcp.consumer.model.ConsumerState;
import com.trgint.pcp.consumer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
@Scope("singleton")
public class ConsumerService implements IConsumerService {
    @Autowired
    private IQueueAPI queueAPI;
    @Autowired
    private IProducerAPI producerAPI;
    private Queue<Consumer> consumersList = new LinkedList<>() ;
    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void consume(Consumer consumer) throws InterruptedException {
        consumer.setState(ConsumerState.BUSY);
        while (true) {
            while (queueAPI.apiCallIsEmpty()) {
                consumer.setState(ConsumerState.IDLE);
                return;
            }
            Task taskToConsume = queueAPI.apiCallTake();
            if(taskToConsume != null) {
                consumer.consumeTask(taskToConsume);
                System.out.println("Consumer " + consumer.getId() + " got task " + taskToConsume.getId());
                //notify producers
                boolean isNotified;
                do {
                    isNotified =  producerAPI.apiCallNotify();
                    if (!isNotified) {
                        System.out.println("Producer not notified, retrying...");
                        Thread.sleep(1000);
                    }
                } while (!isNotified);

            }
        }
    }

    private void consumeOnBackground(Consumer consumer) {
        taskExecutor.execute(() -> {
            try {
                consume(consumer);
            } catch (Exception e) {

            }
        });
    }

    @Override
    public boolean addConsumer() {
        Consumer consumer = new Consumer();
        consumeOnBackground(consumer);
        return consumersList.add(consumer);
    }

    @Override
    public void notifyConsumers(){
        System.out.println("Consumers notified");
        consumersList.stream()
                .filter(consumer -> consumer.getState() == ConsumerState.IDLE)
                .forEach(this::consumeOnBackground);

    }
}
