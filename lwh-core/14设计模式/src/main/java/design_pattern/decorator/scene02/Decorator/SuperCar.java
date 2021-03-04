package design_pattern.decorator.scene02.Decorator;

import design_pattern.decorator.scene02.ICar;

/**
 * Created by Administrator on 2017/2/26.
 * <p>Decorator装饰角色</p>
 */
public class SuperCar implements ICar{

    protected  ICar car;

    public SuperCar(ICar car) {
        super();
        this.car = car;
    }

    @Override
    public void move() {
        car.move();
    }
}
