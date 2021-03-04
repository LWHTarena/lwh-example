package design_pattern.decorator.scene02.impl;

import design_pattern.decorator.scene02.ICar;

/**
 * Created by Administrator on 2017/2/26.
 */
public class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑！");
    }
}
