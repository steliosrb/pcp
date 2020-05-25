package com.trgint.pcp.producer.service;

import com.trgint.pcp.producer.model.Producer;
import com.trgint.pcp.producer.model.ProducerState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ProducerServiceTest {
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private IQueueAPI queueAPI;
    @Mock
    private IConsumerAPI consumerAPI;
    @InjectMocks
    private ProducerService producerService;

    @Test
    public void producerShouldBeInIdleStateIfQueueIsFull() {
        Producer fakeProducer = new Producer();

        when(queueAPI.apiCallIsFull())
                .thenReturn(true);
        try {
            producerService.produce(fakeProducer);
        } catch (InterruptedException e) {

        }
        Assert.assertEquals(fakeProducer.getState(), ProducerState.IDLE);
    }


}
