package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: Oauth2GatewayApplication
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/23 12:49
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2GatewayApplication.class, args);
    }

}
