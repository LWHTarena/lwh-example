package com.lwhtarena.泛型;

/**
 * @author lwh
 * @folder com.lwhtarena.泛型
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * 泛型接口：与继承同理
 * 重写方法随父类而定
 */
public interface Comparable<T> {
    void compare(T t);
}

//声明子类指定具体类型
class Comp implements Comparable<Integer>{

    @Override
    public void compare(Integer integer) {}
}

//擦除
class Comp01 implements Comparable{

    @Override
    public void compare(Object o) {}
}

//父类擦除，子类泛型
class Comp02<T> implements Comparable{

    @Override
    public void compare(Object o) {}
}

//子类泛型 >= 父类泛型
class Comp03<T> implements Comparable<T>{

    @Override
    public void compare(T t) {}
}

