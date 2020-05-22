package com.lwhtarena.spring;

import com.lwhtarena.spring.bean.Person;
import com.lwhtarena.spring.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: MainTest
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 09:21
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person persion =applicationContext.getBean(Person.class);
        System.out.println(persion);

        String[] namesForType =applicationContext.getBeanNamesForType(Person.class);
        for (String name:namesForType){
            System.out.println(name);
        }
    }
}
