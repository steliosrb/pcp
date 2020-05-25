package com.trgint.pcp.producer.controller;

import com.trgint.pcp.producer.service.IProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessagingController.class)
public class MessagingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProducerService producerService;

    @Test
    public void produceShouldReturnSuccessIfServiceRespondsWithTrue() throws Exception {
        when(producerService.addProducer()).thenReturn(true);
        this.mockMvc.perform(get("/producer")).andExpect(status().isOk());
    }

    @Test
    public void produceShouldReturnFailIfServiceRespondsWithFalse() throws Exception {
        when(producerService.addProducer()).thenReturn(false);
        this.mockMvc.perform(get("/producer")).andExpect(status().is5xxServerError());
    }

    @Test
    public void wakeUpShouldReturnSuccessIfServiceRespondsWithTrue() throws Exception {
        when(producerService.addProducer()).thenReturn(true);
        this.mockMvc.perform(get("/wake-up")).andExpect(status().isOk());
    }
}
