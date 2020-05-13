package com.lwhtarena.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: PaymentMain9004
 * @Package com.lwhtarena.springcloud.alibaba
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/13 13:18
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9004.class,args);
    }
}
