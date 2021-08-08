package com.louis.springcloud.controller;

import com.louis.springcloud.entities.CommonResult;
import com.louis.springcloud.entities.Payment;
import com.louis.springcloud.lb.MyLB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("consumer")
public class OrderController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MyLB myLB;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/create")
    public CommonResult<Payment> create(Payment payment){
        System.out.println(payment);
        CommonResult result = restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        return result;
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        return result;
    }

    @GetMapping("/payment/get2/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new CommonResult<>("9999", "操作失败");
        }
    }

    @GetMapping("/payment/getLb")
    public String getLb(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (Objects.isNull(instances) || instances.size()==0) {
            return null;
        } else {
            ServiceInstance instance = myLB.instance(instances);
            URI uri = instance.getUri();
            return restTemplate.getForObject(uri+"/payment/getLb", String.class);
        }
    }
}
