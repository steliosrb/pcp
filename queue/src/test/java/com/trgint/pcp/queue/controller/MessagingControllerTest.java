package com.trgint.pcp.queue.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trgint.pcp.queue.model.Task;
import com.trgint.pcp.queue.service.QueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessagingController.class)
public class MessagingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueueService queueService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void isQueueFullShouldReturnSuccessTrueIfServiceRespondsWithTrue() throws Exception {
        when(queueService.isQueueFull()).thenReturn(true);
        this.mockMvc.perform(get("/queue/is-full"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void isQueueFullShouldReturnSuccessFalseIfServiceRespondsWithFalse() throws Exception {
        when(queueService.isQueueFull()).thenReturn(false);
        this.mockMvc.perform(get("/queue/is-full"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }

    @Test
    public void isQueueEmptyShouldReturnSuccessTrueIfServiceRespondsWithTrue() throws Exception {
        when(queueService.isQueueEmpty()).thenReturn(true);
        this.mockMvc.perform(get("/queue/is-empty"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")));
    }

    @Test
    public void isQueueEmptyShouldReturnSuccessFalseIfServiceRespondsWithFalse() throws Exception {
        when(queueService.isQueueEmpty()).thenReturn(false);
        this.mockMvc.perform(get("/queue/is-empty"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")));
    }


    @Test
    public void addToQueueShouldReturn500IfServiceRespondsWithFalse() throws Exception {
        Task fakeTask = new Task();
        when(queueService.addToQueue(fakeTask)).thenReturn(false);
        this.mockMvc.perform(post("/queue/task")
                .content(asJsonString(fakeTask))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

    }

    @Test
    public void getFromQueueShouldReturnSuccessIfServiceRespondsWithSuccess() throws Exception {
        Task fakeTask = new Task();
        when(queueService.takeFromQueue()).thenReturn(fakeTask);
        this.mockMvc.perform(get("/queue/task"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(fakeTask.getId())));
    }

}
