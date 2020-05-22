package com.lwhtarena.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author liwh
 * @Title: MyBeanPostProcessor
 * @Package com.lwhtarena.spring.bean
 * @Description:
 *      后置处理器：初始化前后进行处理工作
 *      将后置处理器加入到容器中
 * @Version 1.0.0
 * @date 2020/5/22 11:08
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("postProcessBeforeInitialization..."+beanName+"=>"+bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("postProcessAfterInitialization..."+beanName+"=>"+bean);
        return bean;
    }
}
