package design_pattern.decorator.scene02.Decorator;

import design_pattern.decorator.scene02.ICar;

/**
 * Created by Administrator on 2017/2/26.
 * 装饰器
 */
public class FlyCar extends SuperCar {
    public FlyCar(ICar car) {
        super(car);
    }
    public void fly(){
        System.out.println("天上飞！");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}
