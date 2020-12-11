package com.lwhtarena.seata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author liwh
 * @Title: StorageServiceApplication
 * @Package com.lwhtarena.seata
 * @Description:
 * @Version 1.0.0
 * @date 2020/12/9 09:49
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lwhtarena.seata.dao")
public class StorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageServiceApplication.class, args);
    }

}
