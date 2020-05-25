package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Task;
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
    String isQueueFullEndpoint = "/queue/is-full";
    String addOnQueueEndpoint = "/queue/task";

    @Override
    public boolean apiCallIsFull() {
        final String uri = baseUrl + isQueueFullEndpoint;
        Boolean result = restTemplate.getForObject(uri, Boolean.class);
        if (result != null)
            return result;
        return false;

    }

    @Override
    public boolean apiCallAdd(Task task) {
        final String uri = baseUrl + addOnQueueEndpoint;
        ResponseEntity<String> resp = restTemplate.postForEntity(uri, task, String.class);
        return resp.getStatusCode() == HttpStatus.CREATED;
    }


}
