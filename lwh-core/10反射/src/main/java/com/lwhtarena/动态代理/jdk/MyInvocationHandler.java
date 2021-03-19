package com.lwhtarena.动态代理.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 22:53:21
 * @description
 */
public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler() {
        super();
    }
    public MyInvocationHandler(Object target){
        super();
        this.target =target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==================before");
        Object invoke = method.invoke(target, args);
        System.out.println("==================after");
        return invoke;
    }
}
