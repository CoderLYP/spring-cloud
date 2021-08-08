package com.louis.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    public RestTemplate restTemplate;

    @GetMapping("/payment/consul")
    public String payment() {
        return "springCloud with consul:" + port + "\t" + UUID.randomUUID().toString();
    }
}
