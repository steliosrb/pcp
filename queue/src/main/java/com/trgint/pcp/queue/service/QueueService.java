package com.trgint.pcp.queue.service;


import com.trgint.pcp.queue.model.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
@Scope("singleton")
public class QueueService {

    private int size = 10;
    private BlockingQueue<Task> list = new LinkedBlockingDeque<>(size);

    public Boolean isQueueFull() {
        if (list.size() >= size)
            return true;
        return false;
    }

    public Boolean isQueueEmpty() {
        if (list.size() == 0)
            return true;
        return false;
    }

    public Boolean addToQueue(Task task) {
        try {
            if (list.size() < size && task != null) {
                list.put(task);
                return true;
            }
        } catch (InterruptedException e) {
            return false;
        }
        return false;
    }

    public Task takeFromQueue() {
        return list.poll();
    }


}