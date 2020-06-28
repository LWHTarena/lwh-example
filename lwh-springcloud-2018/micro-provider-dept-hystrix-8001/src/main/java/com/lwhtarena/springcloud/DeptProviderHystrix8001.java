package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liwh
 * @Title: DeptProvider8001
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 08:07
 */
@EnableEurekaServer
@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker //对hystrix熔断机制的支持
public class DeptProviderHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001.class,args);
    }
}
