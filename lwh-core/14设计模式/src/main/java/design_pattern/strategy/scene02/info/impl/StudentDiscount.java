package design_pattern.strategy.scene02.info.impl;

import design_pattern.strategy.scene02.info.Discount;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>学生票折扣类：具体策略类</p>
 * @version:v1.0
 */
public class StudentDiscount implements Discount {
    public double calculate(double price) {
        System.out.println("学生票：");
        return price * 0.8;
    }
}
