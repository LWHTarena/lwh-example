package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liwh
 * @Title: ConfigClientMain3355
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/10 10:38
 */
@EnableEurekaClient
@SpringBootApplication
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
