package com.lwhtarena.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author liwh
 * @Title: DeptConnsumerDashboard
 * @Package com.lwhtarena.springcloud
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/28 15:26
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConnsumerDashboard {
    public static void main(String[] args) {
        SpringApplication.run(DeptConnsumerDashboard.class,args);
    }
}
