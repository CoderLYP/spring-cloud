package com.louis.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderGlobalFallbackService implements OrderHystrixService {

    @Override
    public String paymentInfoOK() {
        return "我是OrderGlobalFallbackService paymentInfoOK，系统繁忙，请稍后重试~~";
    }

    @Override
    public String paymentInfoTimeOut() {
        return "我是OrderGlobalFallbackService paymentInfoTimeOut，系统繁忙，请稍后重试~~";
    }
}
