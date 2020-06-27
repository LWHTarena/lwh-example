package com.lwhtarena.dubbo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author liwh
 * @Title: DubboOrderServiceConsumerApplication
 * @Package com.lwhtarena.dubbo
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 07:41
 */
//@EnableDubbo(scanBasePackages = "com.lwhtarena.dubbo" )
@EnableHystrix
@DubboComponentScan(basePackages ="com.lwhtarena.dubbo")
@SpringBootApplication
public class DubboOrderServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboOrderServiceConsumerApplication.class,args);
    }
}
