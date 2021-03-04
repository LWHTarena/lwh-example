package com.lwhtarena.泛型;

/**
 * @author lwh
 * @folder com.lwhtarena.泛型
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * extends:泛型的上限 <= 即子类
 * 1、一般用于限制操作
 * 2、不能使用在添加数据上面，一般都是读取操作
 * 3、规则
 *     List<Fruit> --> List<? extends Fruit>
 *     List<Apple> --> List<? extends Fruit>
 *     List<? extends Apple> --> List<? extends Fruit>
 *
 *    不能存放
 *     List<?>
 *     List<? extends Object>
 */
public class ExtendsTest {

    public static void main(String[] args) {
        //extends 为上限
        Test<Fruit> t1 =new Test<Fruit>();
        Test<Apple> t2 =new Test<Apple>();
        Test<Pear>  t3 =new Test<Pear>();

        //调用方法
        List<? extends Fruit> list1 =new ArrayList<Fruit>();
        test(list1);
        List<Fruit> list2 =new ArrayList<Fruit>();
        test(list2);
        List<Apple> list3 =new ArrayList<Apple>();
        test(list3);

        //? extends Apple
        List<? extends Apple> list4 =new ArrayList<FujiApple>();
        test(list4);
        //? --> 为什么错误，因为？等同于 ？extends Object
        List<?> list5 =new ArrayList<Object>();
        List<? extends Object> list6 =new ArrayList<Object>();
        //test(list6);
    }

    //? extends Fruit
    public static void test(List<? extends Fruit> list){}

    //泛型类
    static class Test<T extends Fruit>{

    }
}
