package com.lwhtarena.泛型;

/**
 * @author lwh
 * @folder com.lwhtarena.泛型
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * super:泛型的下限 >= 即父类或自身
 * 1、一般用于下限操作
 * 2、能够添加数据上面，不能添加父对象
 * 3、规则
 *  List<Fruit> --> List<? super Apple>
 *  List<Apple> --> List<? super Apple>
 *  List<? super Fruit> --> List<? super Apple>
 *  不能存放
 *  List<? super FujiApple> --> List<? super Apple>
 */
public class SuperTest {
    public static void main(String[] args) {

    }
}
