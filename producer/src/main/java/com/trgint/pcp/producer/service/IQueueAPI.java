package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Task;

public interface IQueueAPI {
    boolean apiCallIsFull();
    boolean apiCallAdd(Task task);
}
