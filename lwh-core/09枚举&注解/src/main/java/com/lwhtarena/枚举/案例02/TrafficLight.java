package com.lwhtarena.枚举.案例02;

/**
 * @author lwh
 * @folder com.lwhtarena.枚举.案例01
 * @date 2016/10/6.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class TrafficLight {

    Signal color =Signal.RED;

    public void change(){
        switch (color){
            case  RED:
                color =Signal.GREEN;
                break;
            case YELLOW:
                color =Signal.RED;
                break;
            case GREEN:
                color =Signal.YELLOW;
                break;
        }
    }
}
