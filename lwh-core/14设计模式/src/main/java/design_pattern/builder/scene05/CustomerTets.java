package design_pattern.builder.scene05;

import org.junit.Test;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class CustomerTets {

    @Test
    public void  test(){
        Waiter waiter = new Waiter();
        StarbucksBuilder coffeeBuilder = new CoffeeBuilder();

        //也可以用泡茶builder沏茶
        //StarbucksBuilder teaBuilder = new TeaBuilder();

        waiter.setStarbucksBuilder(coffeeBuilder);
        waiter.constructStarbucks();

        //取到饮料
        Starbucks drink = waiter.getstarbucksDrink();
    }
}
