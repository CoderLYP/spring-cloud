package com.louis.springcloud.controller;

import com.louis.springcloud.entities.CommonResult;
import com.louis.springcloud.entities.Payment;
import com.louis.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result>0) {
            return new CommonResult("0000", "success" + port);
        }
        return new CommonResult("9999", "false");
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        System.out.println(result+"Hello");
        return new CommonResult("0000", "true" + port, result);
    }

    @GetMapping("/lb")
    public String getLb() {
        return port;
    }

    @GetMapping("/feign/timeout")
    public String timeOut() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
