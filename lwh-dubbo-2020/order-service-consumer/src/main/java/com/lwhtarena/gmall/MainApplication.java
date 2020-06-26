package com.lwhtarena.gmall;

import com.lwhtarena.dubbo.service.OrderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author liwh
 * @Title: MainApplication
 * @Package com.lwhtarena.gmall
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/26 08:02
 */
public class MainApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");

        OrderService orderService = applicationContext.getBean(OrderService.class);

        orderService.initOrder("1");
        System.out.println("调用完成....");

        System.in.read();
    }
}
