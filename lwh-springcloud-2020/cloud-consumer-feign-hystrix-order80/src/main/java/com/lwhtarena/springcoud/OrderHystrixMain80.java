package com.lwhtarena.springcoud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liwh
 * @Title: PaymentHystrixMain8001
 * @Package com.lwhtarena.springcoud
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/8 12:47
 */
@SpringBootApplication
@EnableEurekaClient
public class OrderHystrixMain80{
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain80.class,args);
    }
}
