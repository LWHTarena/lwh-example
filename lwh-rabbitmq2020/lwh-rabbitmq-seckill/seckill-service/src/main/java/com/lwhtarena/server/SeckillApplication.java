package com.lwhtarena.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/05/01 08:44:11
 * @description
 */
@MapperScan(basePackages = "com.lwhtarena.model.mapper")
@SpringBootApplication
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class,args);
    }
}
