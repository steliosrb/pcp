package com.trgint.pcp.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducerAPI implements IProducerAPI {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.producer.baseurl}")
    String baseUrl;
    String notifyEndpoint = "/wake-up";

    @Override
    public boolean apiCallNotify() {
        final String uri = baseUrl + notifyEndpoint;
        ResponseEntity<String> resp = restTemplate.getForEntity(uri, String.class);
        return resp.getStatusCode() == HttpStatus.OK;
    }


}
