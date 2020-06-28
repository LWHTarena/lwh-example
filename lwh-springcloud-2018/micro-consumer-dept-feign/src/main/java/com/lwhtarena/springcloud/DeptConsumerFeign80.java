package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liwh
 * @Title: DeptConsumerFeign80
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 15:10
 */
@EnableFeignClients(basePackages = {"com.lwhtarena.springcloud"})
@EnableEurekaClient
@SpringBootApplication
public class DeptConsumerFeign80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerFeign80.class,args);
    }
}
