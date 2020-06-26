package com.lwhtarena.dubbo.service;

import com.lwhtarena.dubbo.bean.UserAddress;

import java.util.List;

/**
 * @author liwh
 * @Title: UserService
 * @Package com.lwhtarena.dubbo.service
 * @Description: 用户服务
 * @Version 1.0.0
 * @date 2020/6/26 07:25
 */
public interface UserService {
    /**
     * 按照用户id返回所有的收货地址
     * @param userId
     * @return
     */
    public List<UserAddress> getUserAddressList(String userId);

}
