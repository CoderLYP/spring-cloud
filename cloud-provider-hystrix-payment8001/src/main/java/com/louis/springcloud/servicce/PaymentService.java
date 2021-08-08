package com.louis.springcloud.servicce;

public interface PaymentService {

    String paymentInfoOK();

    String paymentInfoTimeOut();

    String paymentCircuitBreaker(Long id);
}
