package com.lwhtarena.rpc.test.producer;

/**
 * @author liwh
 * @Title: HelloService
 * @Package com.lwhtarena.rpc.test.producer
 * @Description: 测试服务类
 * @Version 1.0.0
 * @date 2020/9/19 22:32
 */
public interface HelloService {

    String sayHello();

    String sayHello(String message);

    String sayHello(TestBean testBean);
}

