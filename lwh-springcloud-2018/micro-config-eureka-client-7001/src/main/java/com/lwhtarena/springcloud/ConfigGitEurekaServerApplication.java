package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liwh
 * @Title: ConfigGitEurekaServerApplication
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 17:02
 */
@SpringBootApplication
@EnableEurekaServer
public class ConfigGitEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigGitEurekaServerApplication.class,args);
    }
}
