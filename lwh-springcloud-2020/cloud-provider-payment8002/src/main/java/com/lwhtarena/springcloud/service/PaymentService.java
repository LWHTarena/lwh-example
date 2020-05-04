package com.lwhtarena.springcloud.service;

import com.lwhtarena.springcloud.entities.Payment;

/**
 * @author liwh
 * @Title: PaymentService
 * @Package com.lwhtarena.springcloud.service
 * @Description: 服务层
 * @Version 1.0.0
 * @date 2020/5/3 20:18
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
