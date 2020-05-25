package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Task;

public interface IQueueAPI {
    boolean apiCallIsEmpty();
    Task apiCallTake();
}
