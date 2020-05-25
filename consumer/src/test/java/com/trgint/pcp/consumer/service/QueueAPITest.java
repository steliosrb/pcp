package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Task;
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
    public void apiCallIsEmptyShouldReturnTrueIfAPIRespondsWithTrue() {

        when(restTemplate.getForObject(queueAPI.baseUrl + queueAPI.isQueueEmptyEndpoint, Boolean.class))
                .thenReturn(true);

        Boolean result = queueAPI.apiCallIsEmpty();
        Assert.assertEquals(true, result);
    }

    @Test
    public void apiCallIsFullShouldReturnFalseIfAPIRespondsWithError() {

        when(restTemplate.getForObject(queueAPI.baseUrl + queueAPI.isQueueEmptyEndpoint, Boolean.class))
                .thenReturn(null);

        Boolean result = queueAPI.apiCallIsEmpty();
        Assert.assertEquals(false, result);
    }

    @Test
    public void apiCallAddShouldReturnTrueIfAPIRespondsWithTrue() {
        Task fakeTask = new Task();

        when(restTemplate.getForEntity(queueAPI.baseUrl + queueAPI.takeFromQueueEndpoint, Task.class))
                .thenReturn(new ResponseEntity(fakeTask, HttpStatus.OK));

        Task result = queueAPI.apiCallTake();
        Assert.assertEquals(fakeTask, result);
    }

    @Test
    public void apiCallAddShouldReturnFalseIfAPIRespondsWithError() {
        when(restTemplate.getForEntity(queueAPI.baseUrl + queueAPI.takeFromQueueEndpoint, Task.class))
                .thenReturn(new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR));

        Task result = queueAPI.apiCallTake();
        Assert.assertNull(result);
    }


}
