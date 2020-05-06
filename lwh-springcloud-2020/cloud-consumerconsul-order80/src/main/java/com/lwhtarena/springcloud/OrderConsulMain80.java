package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: OrderConsulMain80
 * @Package com.lwhtarena.springcloud
 * @Description: 服务消费者
 * @Version 1.0.0
 * @date 2020/5/5 21:01
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsulMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsulMain80.class,args);
    }
}
