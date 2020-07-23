package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: Oauth2AuthApplication
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/23 12:10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2AuthApplication.class, args);
    }

}
