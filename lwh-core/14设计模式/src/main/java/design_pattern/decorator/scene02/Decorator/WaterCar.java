package design_pattern.decorator.scene02.Decorator;

import design_pattern.decorator.scene02.ICar;

/**
 * Created by Administrator on 2017/2/26.
 * <p>ConcreteDecorator具体装饰角色</p>
 * 装饰器
 */
public class WaterCar extends SuperCar {
    public WaterCar(ICar car) {
        super(car);
    }
    public void swim(){
        System.out.println("水上游！");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}
