package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author liwh
 * @Title: OrderMain80
 * @Package com.lwhtarena.springcloud
 * @Description: 订单服务
 *   在启动该微服务的时候就能加载我们自定义Ribbon配置类，从而使配置生效，形容：
 * @Version 1.0.0
 * @date 2020/5/4 10:15
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MySelfRule.class) //自定义rule
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
