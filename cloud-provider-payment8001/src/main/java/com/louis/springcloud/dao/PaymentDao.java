package com.louis.springcloud.dao;

import com.louis.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author louis
 */
@Mapper
public interface PaymentDao {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
