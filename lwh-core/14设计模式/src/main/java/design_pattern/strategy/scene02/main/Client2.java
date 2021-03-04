package design_pattern.strategy.scene02.main;

import design_pattern.strategy.scene02.info.Discount;
import design_pattern.strategy.scene02.tools.XMLUtil;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class Client2 {
    public static void main(String args[]) {
        MovieTicket mt = new MovieTicket();
        double originalPrice = 60.0;
        double currentPrice;

        mt.setPrice(originalPrice);
        System.out.println("原始价为：" + originalPrice);
        System.out.println("---------------------------------");

        Discount discount;
        discount = (Discount) XMLUtil.getBean(); //读取配置文件并反射生成具体折扣对象
        mt.setDiscount(discount); //注入折扣对象

        currentPrice = mt.getPrice();
        System.out.println("折后价为：" + currentPrice);

        /**
         *  如果需要增加新的打折方式，原有代码均无须修改，只要增加一个新的折扣类作为抽象折扣类的子
         *  类，实现在抽象折扣类中声明的打折方法，然后修改配置文件，将原有具体折扣类类名改为新增折扣
         *  类类名即可，完全符合“开闭原则”。
         */
    }
}
