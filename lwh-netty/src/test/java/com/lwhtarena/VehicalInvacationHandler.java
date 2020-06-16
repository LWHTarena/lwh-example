package com.lwhtarena;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author liwh
 * @Title: VehicalInvacationHandler
 * @Package com.lwhtarena
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/16 05:25
 */

public class VehicalInvacationHandler implements InvocationHandler {

    private final IVehical vehical;
    public VehicalInvacationHandler(IVehical vehical){
        this.vehical = vehical;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------before-------");
        Object invoke = method.invoke(vehical, args);
        System.out.println("---------after-------");

        return invoke;
    }

}
