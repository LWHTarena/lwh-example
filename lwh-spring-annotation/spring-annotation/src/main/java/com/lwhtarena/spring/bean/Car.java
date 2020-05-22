package com.lwhtarena.spring.bean;

import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: Car
 * @Package com.lwhtarena.spring.bean
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 11:04
 */
@Component
public class Car {

    public Car(){
        System.out.println("car constructor...");
    }

    public void init(){
        System.out.println("car ... init...");
    }

    public void detory(){
        System.out.println("car ... detory...");
    }
}
