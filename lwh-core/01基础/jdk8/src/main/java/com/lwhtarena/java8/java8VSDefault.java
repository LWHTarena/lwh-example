package com.lwhtarena.java8;

import org.junit.jupiter.api.Test;

/**
 * <p>
 * <h2>简述：Java 8 默认方法</h2>
 * <pre>
 *     Java 8 新增了接口的默认方法。
 *     简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法。
 *     我们只需在方法名前面加个 default 关键字即可实现默认方法。
 * </pre>
 *
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class java8VSDefault {

    /**默认方法实例**/
    @Test
    public void test001(){
        Vehicle vehicle = new Car2();
        vehicle.print();
    }
}

/**默认方法语法格式*/

interface Vehicle{
    default void print(){
        System.out.println("我有一辆车！");
    }

    /**静态默认方法
     * Java 8 的另一个特性是接口可以声明（并且可以提供实现）静态方法。例如：
     * **/
    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}
interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

/**一个接口有默认方法，考虑这样的情况，一个类实现了多个接口，且这些接口有相同的默认方法，以下实例说明了这种情况的解决方法**/
/**第一个解决方案是创建自己的默认方法，来覆盖重写接口的默认方法：**/
//class Car implements Vehicle, FourWheeler {
//
//    default void print(){
//        System.out.println("我是一辆四轮汽车!");
//    }
//}

/**第二种解决方案可以使用 super 来调用指定接口的默认方法：**/
class Car1 implements Vehicle, FourWheeler {
    @Override
    public void print(){
        Vehicle.super.print();
    }
}

class Car2 implements Vehicle, FourWheeler {
    @Override
    public void print(){
        Vehicle.super.print();
        FourWheeler.super.print();
        Vehicle.blowHorn();
        System.out.println("我是一辆汽车!");
    }
}
