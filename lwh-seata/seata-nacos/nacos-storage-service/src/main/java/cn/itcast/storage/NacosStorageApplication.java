package cn.itcast.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liwh
 */
@MapperScan("cn.itcast.storage.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class NacosStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosStorageApplication.class, args);
    }
}
