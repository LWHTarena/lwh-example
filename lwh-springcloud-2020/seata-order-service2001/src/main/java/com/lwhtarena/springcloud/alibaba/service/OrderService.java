package com.lwhtarena.springcloud.alibaba.service;

import com.lwhtarena.springcloud.alibaba.domain.Order;

/**
 * @author liwh
 * @Title: OrderService
 * @Package com.lwhtarena.springcloud.alibaba.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/14 09:27
 */
public interface OrderService {
    void create(Order order);
}
