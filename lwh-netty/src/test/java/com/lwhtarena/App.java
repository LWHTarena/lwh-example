package com.lwhtarena;

import com.lwhtarena.dubborpc.netty.NettyClient;

import java.lang.reflect.Proxy;

/**
 * @author liwh
 * @Title: App
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 05:26
 */
public class App {
    public static void main(String[] args) {
        IVehical car = new Car();

        NettyClient customer =new NettyClient();
//        IVehical vehical = (IVehical) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{}, new VehicalInvacationHandler(car));
        IVehical vehical = (IVehical) Proxy.newProxyInstance(car.getClass().getClassLoader(), Car.class.getInterfaces(), new VehicalInvacationHandler(car));
        vehical.run();
    }
}
