package com.lwhtarena.泛型;

/**
 * @author lwh
 * @folder com.lwhtarena.泛型
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ? --> 通配符，类型不确定，用于声明变量|形参上
 * 不能用在
 * 1、创建对象
 * 2、创建泛型类 泛型方法 泛型接口上
 */
public class Wildcards {

    public static void main(String[] args) {
        //声明
        List<?> list =new ArrayList<Integer>();
        list =new ArrayList<String>();
        list =new ArrayList<Objects>();
        test(list);

        //编译错误
        //list =new ArrayList<?>();
    }

    //用于在形参上
    public static void test(List<?> list){}

    //不能用在泛型方法上
    //public static <?> void test2(List<?> list){}

    class Test<T>{}

    //不能用在创建泛型类
    //class Test2<?>{}
}
