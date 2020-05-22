package com.lwhtarena.spring;

import com.lwhtarena.spring.bean.Person;
import com.lwhtarena.spring.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author liwh
 * @Title: IOCTest_PropertiesValue
 * @Package com.lwhtarena.spring
 * @Description:测试
 * @Version 1.0.0
 * @date 2020/5/22 16:18
 */
public class IOCTest_PropertiesValue {
    AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);

    @Test
    public void Test01(){
        printBeans(applicationContext);

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment =applicationContext.getEnvironment();
        String property =environment.getProperty("person.nickName");
        System.out.println(property);
        applicationContext.close();
    }

    /**
     * 先打印扫描到的bean
     * @param applicationContext
     */
    private void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames =applicationContext.getBeanDefinitionNames();
        for(String name :definitionNames){
            System.out.println(name);
        }
    }
}
