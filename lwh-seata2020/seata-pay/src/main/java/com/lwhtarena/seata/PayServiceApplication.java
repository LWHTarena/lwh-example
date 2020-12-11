package com.lwhtarena.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: PayServiceApplication
 * @Package com.lwhtarena.seata
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 10:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }
}

