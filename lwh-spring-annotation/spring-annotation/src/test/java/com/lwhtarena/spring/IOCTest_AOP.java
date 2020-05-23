package com.lwhtarena.spring;

import com.lwhtarena.spring.aop.MathCalculator;
import com.lwhtarena.spring.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest_AOP
 * @Package com.lwhtarena.spring
 * @Description: 切面编程
 * @Version 1.0.0
 * @date 2020/5/23 08:59
 */
public class IOCTest_AOP {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mathCalculator =applicationContext.getBean(MathCalculator.class);
        mathCalculator.div(1,0);
        applicationContext.close();
    }
}
