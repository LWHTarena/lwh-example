package com.lwhtarena.反射;

import com.lwhtarena.entity.Person;
import org.junit.Test;

/**
 * @author lwh
 * @folder com.lwhtarena.反射
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * 怎样得到Class对象？
 *   1.直接通过类名.class的方式得到
 *
 *   2.通过对象调用getClass()方式来获取
 *
 *   3.通过全类名的方式获取，用的较多。--->这种在框架里用的最多
 *=====================
 *利用Class对象的newInstance()方法来创建类的一个对象；
 *实际上调用的是类的那个无参数的构造器；
 *一般地，一个类若声明了带参数的构造器，也要声明一个无参数的构造器
 */
public class TestClass {
    @Test
    public void demo01() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        Class clazz =null;
        clazz =Person.class;//1.

        Object obj =new Person();
        clazz =obj.getClass();

        String className ="反射.Person";
        clazz =Class.forName(className);

        Object obj1 =clazz.newInstance();//调用缺省构造函数，返回该Class对象的一个实例
        System.out.println(obj1);
    }
}
