package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author liwh
 * @Title: ConfigCenterMain3344
 * @Package com.lwhtarena.springcloud
 * @Description: 配置中心主启动类
 * @Version 1.0.0
 * @date 2020/5/10 09:37
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class,args);
    }
}
