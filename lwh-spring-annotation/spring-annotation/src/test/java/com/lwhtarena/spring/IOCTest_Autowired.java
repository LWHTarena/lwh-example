package com.lwhtarena.spring;

import com.lwhtarena.spring.config.MainConifgOfAutowired;
import com.lwhtarena.spring.dao.BookDao;
import com.lwhtarena.spring.service.BookService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author liwh
 * @Title: IOCTest_Autowired
 * @Package com.lwhtarena.spring
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 18:25
 */
public class IOCTest_Autowired {

    @Test
    public void Test01(){
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

        BookService bookService =applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        BookDao bookDao =applicationContext.getBean(BookDao.class);
        System.out.println(bookDao);

        applicationContext.close();

    }
}
