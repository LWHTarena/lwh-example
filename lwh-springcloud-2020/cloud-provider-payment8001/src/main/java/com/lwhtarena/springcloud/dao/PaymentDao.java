package com.lwhtarena.springcloud.dao;

import com.lwhtarena.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author liwh
 * @Title: PaymentDao
 * @Package com.lwhtarena.springcloud.dao
 * @Description: 持久层
 * @Version 1.0.0
 * @date 2020/5/3 19:47
 */

@Mapper
public interface PaymentDao {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
