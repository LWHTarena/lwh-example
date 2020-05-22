package com.lwhtarena.spring;

import com.lwhtarena.spring.bean.Yellow;
import com.lwhtarena.spring.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author liwh
 * @Title: IOCTest_Profile
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 20:59
 */
public class IOCTest_Profile {

    /**
     * 1、使用命令行动态参数：在虚拟机参数位置加载-Dspring.profile.active=test
     * 2、使用代码的方式
     *
     */
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] namesForType =applicationContext.getBeanNamesForType(DataSource.class);
        for (String string: namesForType){
            System.out.println(string);
        }

        applicationContext.close();

    }

    /**
     * 使用代码的方式
     */
    @Test
    public void test02(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext();
        //1、创建一个容器applicationContext；
        //2、设置激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test","dev","prod");
        //3、注册配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4、启动刷新容器
        applicationContext.refresh();

        String[] namesForType =applicationContext.getBeanNamesForType(DataSource.class);
        for (String string: namesForType){
            System.out.println(string);
        }

        Yellow yellow =applicationContext.getBean(Yellow.class);
        System.out.println(yellow);

        applicationContext.close();

    }
}
