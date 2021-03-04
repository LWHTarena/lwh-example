package design_pattern.decorator.scene02.Decorator;

import design_pattern.decorator.scene02.ICar;

/**
 * Created by Administrator on 2017/2/26.
 * <p>ConcreteDecorator具体装饰角色</p>
 * AICar 就是装饰器
 */
public class AICar extends SuperCar {
    public AICar(ICar car) {
        super(car);
    }

    public void autoMove(){
        System.out.println("自动跑！");
    }

    @Override
    public void move() {
        super.move();
        autoMove();
    }
}
