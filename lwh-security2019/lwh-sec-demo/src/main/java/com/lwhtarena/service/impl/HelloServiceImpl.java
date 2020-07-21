package com.lwhtarena.service.impl;

import com.lwhtarena.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author liwh
 * @Title: HelloServiceImpl
 * @Package com.lwhtarena.service.impl
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/20 22:28
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello "+name;
    }

}
