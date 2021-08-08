package com.louis.springcloud.servicce;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfoOK() {
        return "线程池:" + Thread.currentThread().getName() + " getInfoOk";
    }

    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    @Override
    public String paymentInfoTimeOut() {
//        int timeNumber = 2;
//        try {
//            // 暂停3秒钟
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "线程池:" + Thread.currentThread().getName() + " getInfoTimeOut, 耗时(秒)" + timeNumber;
        return "线程池:" + Thread.currentThread().getName() + " getInfoTimeOut";
    }

    public String paymentInfoTimeOutHandler() {
        return "线程池:" + Thread.currentThread().getName() + " 系统繁忙，请稍后再试~~";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    @Override
    public String paymentCircuitBreaker(Long id) {
        if (id<0) {
            throw new RuntimeException("id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "调用成功，流水号：" + uuid;
    }

    public String paymentCircuitBreakerFallback(Long id) {
        return "id不能为负数，请稍后再试~~";
    }
}
