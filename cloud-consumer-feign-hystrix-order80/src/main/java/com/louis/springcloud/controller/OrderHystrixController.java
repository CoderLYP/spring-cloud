package com.louis.springcloud.controller;

import com.louis.springcloud.service.OrderHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
@DefaultProperties(defaultFallback = "paymentInfoGlobalFallback")
public class OrderHystrixController {

    @Autowired
    private OrderHystrixService orderHystrixService;

    @GetMapping("/hystrix/ok")
    //@HystrixCommand
    public String paymentInfoOK() {
        //int age = 10/0;
        String result = orderHystrixService.paymentInfoOK();
        return result;
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @GetMapping("/hystrix/timeout")
    public String paymentInfoTimeOut() {
        int age = 10/0;
        String result = orderHystrixService.paymentInfoTimeOut();
        return result;
    }

    public String paymentInfoTimeOutHandler() {
        return "我是消费端80，系统繁忙，请稍后再试~~";
    }

    public String paymentInfoGlobalFallback() {
        return "我是Global Fallback，系统繁忙，请稍后再试~~";
    }

}
