package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author liwh
 * @Title: Zuul9527
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 16:04
 */

@EnableZuulProxy
@SpringBootApplication
public class Zuul9527 {
    public static void main(String[] args) {
        SpringApplication.run(Zuul9527.class,args);
    }
}
