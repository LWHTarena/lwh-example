package com.lwhtarena.嵌套;

/**
 * @author lwh
 * @folder com.lwhtarena.嵌套
 * @date 2016/10/3.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class Student<T> {

    T score;

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }
}
