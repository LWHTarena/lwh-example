package com.lwhtarena.pxe;

import com.lwhtarena.pxe.domain.IpmiBean;
import com.lwhtarena.pxe.domain.Net;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */


/*******
 * 程序入口
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {Net.class, IpmiBean.class})
public class Application {
    /**
     * Spring Boot建议将我们main方法所在的这个主要的配置类配置在根包名下。
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
/***************************************************************************
 * @RestController:
 *   controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
 * @EnableAutoConfiguration:
 *   这个注解告诉Spring Boot根据添加的jar依赖猜测你想如何配置Spring。
 *
 * @SpringBootApplication：
 *   是Spring Boot项目的核心注解，主要目的是开启自动配置。
 * */
