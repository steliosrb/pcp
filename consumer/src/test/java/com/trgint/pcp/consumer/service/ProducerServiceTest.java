package com.trgint.pcp.consumer.service;

import com.trgint.pcp.consumer.model.Consumer;
import com.trgint.pcp.consumer.model.ConsumerState;
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
    private IProducerAPI producerAPI;
    @InjectMocks
    private ConsumerService consumerService;

    @Test
    public void consumerShouldBeInIdleStateIfQueueIsEmpty() {
        Consumer fakeConsumer = new Consumer();

        when(queueAPI.apiCallIsEmpty())
                .thenReturn(true);
        try {
            consumerService.consume(fakeConsumer);
        } catch (InterruptedException e) {

        }
        Assert.assertEquals(fakeConsumer.getState(), ConsumerState.IDLE);
    }


}
