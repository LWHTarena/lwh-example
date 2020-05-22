package com.lwhtarena.spring;

import com.lwhtarena.spring.bean.Blue;
import com.lwhtarena.spring.config.MainConfig;
import com.lwhtarena.spring.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 09:36
 */
public class IOCTest {

    @Test
    public void test01(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] definitionNames =applicationContext.getBeanDefinitionNames();
        for (String name :definitionNames){
            System.out.println(name);
        }

        Object bean = applicationContext.getBean("persion");
        Object bean2 = applicationContext.getBean("persion");
        System.out.println(bean==bean2);

    }

    @Test
    public void test02(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames =applicationContext.getBeanDefinitionNames();
        for (String name :definitionNames){
            System.out.println(name);
        }
    }

    @Test
    public void test03(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] namesForType =applicationContext.getBeanDefinitionNames();
        for (String name :namesForType){
            System.out.println(name);
        }

//        Map<String, Person> persons =applicationContext.getBeansOfType();
//        System.out.println();
    }

    @Test
    public void testImport(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Blue blue =applicationContext.getBean(Blue.class);
        System.out.println(blue);

        /**
         * 工厂bean获取的是调用getObject创建的对象
         */
        Object bean2 =applicationContext.getBean("colorFactoryBean");
        System.out.println("bean类型："+bean2.getClass());

        /**
         * 通过获取&获取工厂bean
         */
        Object bean4 =applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean类型："+bean4.getClass());
    }
}
