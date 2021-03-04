package com.lwhtarena.List;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lwh
 * @folder com.lwhtarena.List
 * @date 2016/10/2.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TestList {

    public static void main(String[] args) {
        List list =new ArrayList();

        list.add("aaa");
        list.add(new Date());
        list.add(new Dog());
        list.add(1234);//包装类的：自动装箱

        System.out.println(list.size());
        list.remove(new Date());
        System.out.println(list.size());

        List list2 =new ArrayList();
        list2.add("bbb");
        list2.add("ccc");

        list.add(list2);

        System.out.println(list.get(3));
        System.out.println(list.size());


    }
}

class Dog{
    private String name;
}
