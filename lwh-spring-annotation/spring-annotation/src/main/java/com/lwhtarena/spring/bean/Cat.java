package com.lwhtarena.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author liwh
 * @Title: Cat
 * @Package com.lwhtarena.spring.bean
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 11:04
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
    public Cat(){
        System.out.println("cat constructor...");
    }

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...afterPropertiesSet...");
    }
}
