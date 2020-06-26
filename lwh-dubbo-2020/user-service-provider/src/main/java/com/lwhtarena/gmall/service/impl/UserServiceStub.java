package com.lwhtarena.gmall.service.impl;

import com.lwhtarena.dubbo.bean.UserAddress;
import com.lwhtarena.dubbo.service.UserService;
import org.apache.dubbo.common.utils.StringUtils;

import java.util.List;

/**
 * @author liwh
 * @Title: UserServiceStub
 * @Package com.lwhtarena.gmall.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 07:58
 */
public class UserServiceStub implements UserService {
    private final UserService userService;

    /**
     * 传入的是userService远程的代理对象
     * @param userService
     */
    public UserServiceStub(UserService userService) {
        this.userService = userService;
    }


    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("UserServiceStub.....");
        if(!StringUtils.isEmpty(userId)) {
            return userService.getUserAddressList(userId);
        }
        return null;
    }
}
