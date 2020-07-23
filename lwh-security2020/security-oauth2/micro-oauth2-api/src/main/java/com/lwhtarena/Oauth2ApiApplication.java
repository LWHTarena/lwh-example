package com.lwhtarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 * @Title: Oauth2ApiApplication
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/7/23 13:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Oauth2ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2ApiApplication.class, args);
    }

}