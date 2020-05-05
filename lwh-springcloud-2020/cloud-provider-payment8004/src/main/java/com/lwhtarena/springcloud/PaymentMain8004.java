package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: PayMentMain8004
 * @Package com.lwhtarena.springcloud
 * @Description: zookeeper注册中心
 * @Version 1.0.0
 * @date 2020/5/5 13:06
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于使用consul 或 zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
