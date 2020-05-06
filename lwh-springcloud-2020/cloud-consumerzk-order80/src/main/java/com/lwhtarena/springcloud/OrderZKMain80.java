package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: OrderZKMain80
 * @Package com.lwhtarena.springcloud
 * @Description: zookeeper 服务的消费者
 * @Version 1.0.0
 * @date 2020/5/5 17:05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKMain80.class,args);
    }
}
