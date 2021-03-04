package design_pattern.decorator.scene02;

import design_pattern.decorator.scene02.Decorator.FlyCar;
import design_pattern.decorator.scene02.Decorator.WaterCar;
import design_pattern.decorator.scene02.impl.Car;

/**
 * Created by Administrator on 2017/2/26.
 */
public class Client {
    public static void main(String[] args) {
        Car car =new Car();
        car.move();

        System.out.println("增加新的功能，飞行----------");
        FlyCar flyCar =new FlyCar(car);
        flyCar.move();

        System.out.println("增加新的功能，水里游---------");
        WaterCar  waterCar = new WaterCar(car);
        waterCar.move();

        System.out.println("增加两个新的功能，飞行，水里游-------");
        WaterCar waterCar2 = new WaterCar(new FlyCar(car));
        waterCar2.move();
    }
}
