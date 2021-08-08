package com.louis.springcloud.service;

import com.louis.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface OrderFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    String timeOut();
}
