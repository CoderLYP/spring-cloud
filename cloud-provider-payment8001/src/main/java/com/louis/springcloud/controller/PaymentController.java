package com.louis.springcloud.controller;

import cn.hutool.json.JSON;
import com.louis.springcloud.entities.CommonResult;
import com.louis.springcloud.entities.Payment;
import com.louis.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/getDiscoveryClient")
    public Object getDiscoveryClient() {
        List<String> services = discoveryClient.getServices();
        System.out.println(services);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        System.out.println(instances);
        return discoveryClient;
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
