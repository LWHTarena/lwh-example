package com.lwhtarena.sc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liwh
 * @Title: ServiceDocApplication
 * @Package com.lwhtarena.sc
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/17 23:35
 */
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class ServiceDocApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceDocApplication.class, args);
    }
}