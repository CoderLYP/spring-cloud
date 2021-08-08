package com.louis.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author louis
 * @description 创建自己的负载均衡接口
 */
public interface MyLB {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
