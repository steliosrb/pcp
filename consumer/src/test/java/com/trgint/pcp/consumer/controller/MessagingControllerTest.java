package com.trgint.pcp.consumer.controller;

import com.trgint.pcp.consumer.service.IConsumerService;
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
    private IConsumerService consumerService;

    @Test
    public void consumeShouldReturnSuccessIfServiceRespondsWithTrue() throws Exception {
        when(consumerService.addConsumer()).thenReturn(true);
        this.mockMvc.perform(get("/consumer")).andExpect(status().isOk());
    }

    @Test
    public void consumeShouldReturnFailIfServiceRespondsWithFalse() throws Exception {
        when(consumerService.addConsumer()).thenReturn(false);
        this.mockMvc.perform(get("/consumer")).andExpect(status().is5xxServerError());
    }

    @Test
    public void wakeUpShouldReturnSuccessIfServiceRespondsWithTrue() throws Exception {
        when(consumerService.addConsumer()).thenReturn(true);
        this.mockMvc.perform(get("/wake-up")).andExpect(status().isOk());
    }
}
