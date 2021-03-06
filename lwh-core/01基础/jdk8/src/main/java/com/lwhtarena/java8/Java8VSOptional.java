package com.lwhtarena.java8;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * <p>
 * <h2>简述：Java 8 Optional 类</h2>
 * <ol>Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。</ol>
 * <ol>Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。</ol>
 * <ol>Optional 类的引入很好的解决空指针异常</ol>
 *
 *
 *
 * Optional 类的引入很好的解决空指针异常
 * <h2>功能描述：Java 8 Optional 类</h2>
 * <ol></ol>
 * <blockquote><pre></pre></blockquote>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Java8VSOptional {

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    @Test
    public void test001(){
        Integer val =null;
        Integer val2 =new Integer(10);

        /**Optional.ofNullable - 允许传递为 null 参数**/
        Optional<Integer> a =Optional.ofNullable(val);

        /** Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException**/
        Optional<Integer> b =Optional.of(val2);
        System.out.println(Java8VSOptional.sum(a,b));

    }
}
