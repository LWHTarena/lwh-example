package com.lwhtarena.dubbo.service.impl;

import com.lwhtarena.dubbo.bean.UserAddress;
import com.lwhtarena.dubbo.service.OrderService;
import com.lwhtarena.dubbo.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author liwh
 * @Title: OrderServiceImpl
 * @Package com.lwhtarena.dubbo.service.impl
 * @Description:
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.7.7）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 *
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @Version 1.0.0
 * @date 2020/6/26 07:44
 */
@Service
public class OrderServiceImpl implements OrderService {

    @DubboReference(loadbalance="random",timeout=1000,version = "1.0.0")
            UserService userService;
    @HystrixCommand(fallbackMethod="hello")
    @Override
    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id："+userId);
        //1、查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        return addressList;
    }


    public List<UserAddress> hello(String userId) {
        return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
    }
}
