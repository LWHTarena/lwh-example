package com.lwhtarena.springmvc.service;

import org.springframework.stereotype.Service;

/**
 * @author liwh
 * @Title: HelloService
 * @Package com.lwhtarena.springmvc.service
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 13:10
 */

@Service
public class HelloService {

    public String sayHello(String name){

        return "Hello "+name;
    }

}
