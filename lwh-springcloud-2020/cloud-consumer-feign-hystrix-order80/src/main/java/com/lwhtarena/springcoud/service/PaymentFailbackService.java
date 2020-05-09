package com.lwhtarena.springcoud.service;

import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: PaymentFailbackService
 * @Package com.lwhtarena.springcoud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/8 17:40
 */
@Component
public class PaymentFailbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "=======PaymentFailbackService paymentInfo_OK =====";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "=======PaymentFailbackService paymentInfo_Timeout =====";
    }
}
