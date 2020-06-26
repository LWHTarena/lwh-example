package com.lwhtarena.gmall;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author liwh
 * @Title: MainApplication
 * @Package com.lwhtarena.gmall
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 07:55
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
        ioc.start();
        System.in.read();
    }
}
