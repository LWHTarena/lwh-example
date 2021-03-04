package com.lwhtarena.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <h2>简述：Java 8 方法引用</h2>
 * <ol>方法引用通过方法的名字来指向一个方法。</ol>
 * <ol>方法引用可以使语言的构造更紧凑简洁，减少冗余代码。</ol>
 * <ol>方法引用使用一对冒号 :: 。</ol>
 *
 * <h2>功能描述：</h2>
 * <ol>构造器引用：它的语法是Class::new，或者更一般的Class< T >::new实例如下：</ol>
 * <blockquote><pre>
 *       final Car car = Car.create( Car::new );
 *       final List< Car > cars = Arrays.asList( car );
 * </pre></blockquote>
 *
 * <ol>静态方法引用：它的语法是Class::static_method，实例如下：</ol>
 * <blockquote><pre>
 *     cars.forEach( Car::collide );
 * </pre></blockquote>
 *
 * <ol>特定类的任意对象的方法引用：它的语法是Class::method实例如下：</ol>
 * <blockquote><pre>
 *     cars.forEach( Car::repair );
 * </pre></blockquote>
 *
 * <ol>特定对象的方法引用：它的语法是instance::method实例如下：</ol>
 * <blockquote><pre>
 *     final Car police = Car.create( Car::new );
 *     cars.forEach( police::follow );
 * </pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSMethod {

    public void test001(){
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        /**我们将 System.out::println 方法作为静态方法来引用。**/
        names.forEach(System.out::println);
    }


}



@FunctionalInterface
interface Supplier<T> {
    T get();
}

/**我们在Car类中定义了4个方法作为例子来区分Java中4种不同方法的引用。**/
class Car {

    /**Supplier是jdk1.8的接口，这里和lamda一起使用了**/
    public static Car create(final  Supplier<Car> supplier){
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}

