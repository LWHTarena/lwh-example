package cn.itcast.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liwh
 */
@MapperScan("cn.itcast.account.mapper")
@SpringBootApplication
public class NacosAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosAccountApplication.class, args);
    }
}
