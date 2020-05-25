package com.trgint.pcp.producer.service;

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
public class ConsumerAPITest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private ConsumerAPI consumerAPI;


    @Test
    public void apiCallNotifyShouldReturnTrueIfAPIRespondsWithTrue() {


        when(restTemplate.getForEntity(consumerAPI.baseUrl + consumerAPI.notifyEndpoint, String.class))
                .thenReturn(new ResponseEntity("", HttpStatus.OK));

        Boolean result = consumerAPI.apiCallNotify();
        Assert.assertEquals(true, result);
    }

    @Test
    public void apiCallNotifyShouldReturnFalseIfAPIRespondsWithError() {

        when(restTemplate.getForEntity(consumerAPI.baseUrl + consumerAPI.notifyEndpoint, String.class))
                .thenReturn(new ResponseEntity("", HttpStatus.INTERNAL_SERVER_ERROR));

        Boolean result = consumerAPI.apiCallNotify();
        Assert.assertEquals(false, result);
    }


}
