package com.lwhtarena.dubbo.service;

import com.lwhtarena.dubbo.bean.UserAddress;

import java.util.List;

/**
 * @author liwh
 * @Title: OrderService
 * @Package com.lwhtarena.dubbo.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 07:25
 */
public interface OrderService {
    /**
     * 初始化订单
     * @param userId
     */
    public List<UserAddress> initOrder(String userId);
}
