package com.louis.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = OrderGlobalFallbackService.class)
public interface OrderHystrixService {

    @GetMapping("/payment/hystrix/ok")
    String paymentInfoOK();

    @GetMapping("/payment/hystrix/timeout")
    String paymentInfoTimeOut();
}
