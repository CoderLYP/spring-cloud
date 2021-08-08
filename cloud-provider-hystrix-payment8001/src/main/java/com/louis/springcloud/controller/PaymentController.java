package com.louis.springcloud.controller;

import com.louis.springcloud.servicce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/hystrix/ok")
    public String paymentInfoOK() {
        String result = paymentService.paymentInfoOK();
        log.info("*****result:" + result);
        return result;
    }

    @GetMapping("/hystrix/timeout")
    public String paymentInfoTimeOut() {
        String result = paymentService.paymentInfoTimeOut();
        log.info("*****result:" + result);
        return result;
    }

    @GetMapping("/hystrix/circuit/{id}")
    public String paymentInfoTimeOut(@PathVariable("id") Long id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result:" + result);
        return result;
    }

}
