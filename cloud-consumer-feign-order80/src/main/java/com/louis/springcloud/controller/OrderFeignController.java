package com.louis.springcloud.controller;

import com.louis.springcloud.entities.CommonResult;
import com.louis.springcloud.service.OrderFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consumer")
public class OrderFeignController {

    @Autowired
    private OrderFeignService orderFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        CommonResult result = orderFeignService.getPaymentById(id);
        return result;
    }

    @GetMapping("/payment/feign/timeout")
    public String timeOut() {
       return orderFeignService.timeOut();
    }
}
