package com.lwhtarena.注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author lwh
 * @folder com.lwhtarena.注解
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 * 1、使用@interface 定义注解
 * 2、使用类似于接口方法声明的方式来定义注解的属性：其中返回值称为属性的类型
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
public @interface HelloAnnotation {

    public  String name() default "lwhtarena";
}

