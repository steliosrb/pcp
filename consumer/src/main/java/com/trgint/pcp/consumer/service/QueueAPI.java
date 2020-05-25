package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QueueAPI implements IQueueAPI {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.queue.baseurl}")
    String baseUrl;
    String isQueueEmptyEndpoint = "/queue/is-empty";
    String takeFromQueueEndpoint = "/queue/task";

    @Override
    public boolean apiCallIsEmpty() {
        final String uri = baseUrl + isQueueEmptyEndpoint;
        Boolean result = restTemplate.getForObject(uri, Boolean.class);
        if(result != null)
            return result;
        return false;

    }

    @Override
    public Task apiCallTake() {
        final String uri = baseUrl + takeFromQueueEndpoint;
        ResponseEntity<Task> resp =  restTemplate.getForEntity(uri,Task.class);
        return resp.getStatusCode() == HttpStatus.OK ? resp.getBody() : null;
    }


}
