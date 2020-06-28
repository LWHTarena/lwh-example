package com.lwhtarena.springcloud;

import com.lwhtarena.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author liwh
 * @Title: DeptConsumer80
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 11:13
 */
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name = "MICRO-CONSUMER-DEPT-80",configuration = MySelfRule.class)
public class DeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80.class,args);
    }
}
