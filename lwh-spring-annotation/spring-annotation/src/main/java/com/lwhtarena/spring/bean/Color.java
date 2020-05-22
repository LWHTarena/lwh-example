package com.lwhtarena.spring.bean;

/**
 * @author liwh
 * @Title: Color
 * @Package com.lwhtarena.spring.bean
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/22 11:05
 */
public class Color {
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Color [car=" + car + "]";
    }
}
