package design_pattern.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/2/25.
 */
public class Client {
    public static void main(String[] args) {
        Star realStar = new RealStar();
        StarHandler handler =new StarHandler(realStar);

        //代理对象
        Star proxy = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{Star.class},handler);

        proxy.sing();
    }
}
