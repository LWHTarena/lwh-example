package com.lwhtarena.动态代理.jdk;

import java.lang.reflect.Proxy;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 22:57:18
 * @description
 */
public class Test {
    public static void main(String[] args) {
        AService service = new AService();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(service);
        Service aServiceProxy = (Service) Proxy.newProxyInstance(
                service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                myInvocationHandler);
        aServiceProxy.add();
        System.out.println("<---------------->");
        aServiceProxy.update();

    }
}
