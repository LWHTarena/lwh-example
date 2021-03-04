package com.lwhtarena.枚举.案例01;

/**
 * @author lwh
 * @folder com.lwhtarena.枚举.案例01
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TestMain {

    public static void main(String[] args) {

        Season SPRING = Season.SPRING;
		System.out.println(SPRING);
		System.out.println(Season.SPRING);

        //所有的枚举类都提供了一个 values 方法, 该方法可以很方便地遍历所有的枚举值
        for(Season2 s: Season2.values()){
            System.out.println(s.getTimeInfo());
        }

        String str = "WINTER";

        //使用一个字符串获取对应的枚举类对象, 可以使用 valueof 方法
        Season2 s = Season2.valueOf(Season2.class, str);
        System.out.println(s.getName());
    }
}
