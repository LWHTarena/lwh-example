package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: PaymentConsulMain8006
 * @Package com.lwhtarena.springcloud
 * @Description:  服务提供者
 * @Version 1.0.0
 * @date 2020/5/5 20:47
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain8006.class,args);
    }
}
