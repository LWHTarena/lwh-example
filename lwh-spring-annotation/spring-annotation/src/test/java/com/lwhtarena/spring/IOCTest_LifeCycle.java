package com.lwhtarena.spring;

import com.lwhtarena.spring.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest_LifeCycle
 * @Package com.lwhtarena.spring
 * @Description: 测试bean的生命周期
 * @Version 1.0.0
 * @date 2020/5/22 15:23
 */
public class IOCTest_LifeCycle {

    @Test
    public void test01(){
        /**1、创建ioc容器**/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("创建容器完成...");

    }

}
