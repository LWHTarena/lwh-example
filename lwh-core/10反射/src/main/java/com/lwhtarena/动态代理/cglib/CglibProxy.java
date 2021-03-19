package com.lwhtarena.动态代理.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liwh
 * @version 1.0
 * @date 2021/03/19 23:02:50
 * @description 代理类
 */
public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(">>>>>>>>>>>>>>>>>>>> before");
        //执行目标add方法
        methodProxy.invokeSuper(o,args);
        System.out.println("<<<<<<<<<<<<<<<<<<<< after");
        return null;
    }
}
