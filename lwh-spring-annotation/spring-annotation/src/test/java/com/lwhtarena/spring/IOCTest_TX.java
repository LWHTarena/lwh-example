package com.lwhtarena.spring;

import com.lwhtarena.spring.tx.TxConfig;
import com.lwhtarena.spring.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest_TX
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 09:26
 */
public class IOCTest_TX {

    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext =new AnnotationConfigApplicationContext(TxConfig.class);
        UserService service =applicationContext.getBean(UserService.class);
        service.insertUser();
        applicationContext.close();
    }
}
