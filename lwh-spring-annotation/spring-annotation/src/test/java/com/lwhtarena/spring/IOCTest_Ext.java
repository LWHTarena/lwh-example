package com.lwhtarena.spring;

import com.lwhtarena.spring.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest_Ext
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:30
 */
public class IOCTest_Ext {
    @Test
    public void test(){
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(ExtConfig.class);
        applicationContext.close();
    }
}
