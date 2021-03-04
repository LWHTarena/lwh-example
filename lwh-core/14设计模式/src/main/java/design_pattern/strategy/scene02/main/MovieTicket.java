package design_pattern.strategy.scene02.main;

import design_pattern.strategy.scene02.info.Discount;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>电影票类：环境类</p>
 * @version:v1.0
 */
public class MovieTicket {
    private double price;
    private Discount discount; //维持一个对抽象折扣类的引用

    public void setPrice(double price) {
        this.price = price;
    }

    //注入一个折扣类对象
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public double getPrice() {
        //调用折扣类的折扣价计算方法
        return discount.calculate(this.price);
    }
}
