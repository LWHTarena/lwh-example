package com.lwhtarena.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: SentinelMain8401
 * @Package com.lwhtarena.springcloud.alibaba
 * @Description:  服务主启动类
 * @Version 1.0.0
 * @date 2020/5/12 17:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8401.class,args);
    }
}
