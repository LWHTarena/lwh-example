package design_pattern.proxy.scene02;

import design_pattern.proxy.scene02.dynamic.ITarget;
import design_pattern.proxy.scene02.dynamic.TargetImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestClient {


    @Test
    public void test(){
        //创建红酒生产工厂
        IRedWine redWine =new RealRedWineFactory();

        //工厂生产红酒
        redWine.product();

        //工厂销售红酒
        redWine.sell();
    }

    @Test
    public void testProxy(){

        //创建真实的红酒产对象实例
        IRedWine  realRedWineFactory=new RealRedWineFactory();

        //获取代理对象实例
        IRedWine redWineProxy =new RedWineProxy(realRedWineFactory);

        //代理商生产红酒（其实真实生产的是工厂）
        redWineProxy.product();

        //代理商销售红酒（批发价拿货，然后以高价格出售，获取差价利润）
        redWineProxy.sell();
    }

    /**
     * 动态代理客户端应用程序
     */
    @Test
    public void testDynamicClient(){
        InvocationHandler handler =new TargetImpl();
        ITarget target = (ITarget) Proxy.newProxyInstance(ITarget.class.getClassLoader(),new Class[]{ITarget.class},handler);

        /**
         * 操作方法
         */
        target.operation();
    }
}
