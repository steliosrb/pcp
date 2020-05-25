package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Task;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class QueueAPITest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private QueueAPI queueAPI;

    @Test
    public void apiCallIsFullShouldReturnTrueIfAPIRespondsWithTrue() {


        when(restTemplate.getForObject(queueAPI.baseUrl + queueAPI.isQueueFullEndpoint, Boolean.class))
                .thenReturn(true);

        Boolean result = queueAPI.apiCallIsFull();
        Assert.assertEquals(true, result);
    }

    @Test
    public void apiCallIsFullShouldReturnFalseIfAPIRespondsWithError() {

        when(restTemplate.getForObject(queueAPI.baseUrl + queueAPI.isQueueFullEndpoint, Boolean.class))
                .thenReturn(null);

        Boolean result = queueAPI.apiCallIsFull();
        Assert.assertEquals(false, result);
    }

    @Test
    public void apiCallAddShouldReturnTrueIfAPIRespondsWithTrue() {
        Task fakeTask = new Task();
        when(restTemplate.postForEntity(queueAPI.baseUrl + queueAPI.addOnQueueEndpoint, fakeTask, String.class))
                .thenReturn(new ResponseEntity("", HttpStatus.CREATED));

        Boolean result = queueAPI.apiCallAdd(fakeTask);
        Assert.assertEquals(true, result);
    }

    @Test
    public void apiCallAddShouldReturnFalseIfAPIRespondsWithError() {
        Task fakeTask = new Task();
        when(restTemplate.postForEntity(queueAPI.baseUrl + queueAPI.addOnQueueEndpoint, fakeTask, String.class))
                .thenReturn(new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR));

        Boolean result = queueAPI.apiCallAdd(fakeTask);
        Assert.assertEquals(false, result);
    }


}
