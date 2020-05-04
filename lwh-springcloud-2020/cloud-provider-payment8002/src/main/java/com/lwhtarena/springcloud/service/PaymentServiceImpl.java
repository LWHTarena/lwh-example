package com.lwhtarena.springcloud.service;

import com.lwhtarena.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lwhtarena.springcloud.dao.PaymentDao;

import javax.annotation.Resource;

/**
 * @author liwh
 * @Title: PaymentServiceImpl
 * @Package com.lwhtarena.springcloud.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/3 20:19
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
