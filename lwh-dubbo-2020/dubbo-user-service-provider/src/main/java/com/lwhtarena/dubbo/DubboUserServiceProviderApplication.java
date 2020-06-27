package com.lwhtarena.dubbo;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author liwh
 * @Title: DubboUserServiceProviderApplication
 * @Package com.lwhtarena.dubbo
 * @Description:
 *
 * 1、导入依赖；
 * 		1）、导入dubbo-starter
 * 		2）、导入dubbo的其他依赖
 *
 * SpringBoot与dubbo整合的三种方式：
 * 1）、导入dubbo-starter，在application.properties配置属性，使用@DubboService【暴露服务】使用@DuuboReference【引用服务】
 * 2）、保留dubbo xml配置文件;
 * 		导入dubbo-starter，使用@ImportResource导入dubbo的配置文件即可
 * 3）、使用注解API的方式：
 * 		将每一个组件手动创建到容器中,让dubbo来扫描其他的组件
 *
 * @Version 1.0.0
 * @date 2020/6/26 06:55
 */
//@EnableDubbo //第一种方式，开启基于注解的dubbo功能 (配置足够简单才推荐使用，一般不推荐使用)
//@ImportResource(locations = "classpath:provider.xml") //第二种方式
@DubboComponentScan(basePackages="com.lwhtarena.dubbo") //第三种方式 //必须配置包扫描、否则Dubbo无法注册服务，实现类加扫描也不行，日狗不是
@EnableHystrix //开启服务容错
@SpringBootApplication
public class DubboUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboUserServiceProviderApplication.class, args);
    }
}
