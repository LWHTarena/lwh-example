package design_pattern.strategy.scene02.error;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>电影票类</p>
 * @version:v1.0
 */
public class MovieTicket {
    private double price; //电影票价格
    private String type; //电影票类型

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return this.calculate();
    }

    //计算打折之后的票价
    public double calculate() {
        //学生票折后票价计算
        if (this.type.equalsIgnoreCase("student")) {
            System.out.println("学生票：");
            return this.price * 0.8;
        }
        //儿童票折后票价计算
        else if (this.type.equalsIgnoreCase("children") && this.price >= 20) {
            System.out.println("儿童票：");
            return this.price - 10;
        }
        //VIP票折后票价计算
        else if (this.type.equalsIgnoreCase("vip")) {
            System.out.println("VIP票：");
            System.out.println("增加积分！");
            return this.price * 0.5;
        } else {
            return this.price; //如果不满足任何打折要求，则返回原始票价
        }
    }
    /**
     * 通过MovieTicket类实现了电影票的折后价计算，该方案解决了电影票打折问题，每一种打折方式都可以称为一
     * 种打折算法，更换打折方式只需修改客户端代码中的参数，无须修改已有源代码，但该方案并不是一个完美的解决
     * 方案，它至少存在如下三个问题：
     *
     * (1) MovieTicket类的calculate()方法非常庞大，它包含各种打折算法的实现代码，在代码中出现了较长的if…else…语句，
     *  不利于测试和维护。
     *
     * (2) 增加新的打折算法或者对原有打折算法进行修改时必须修改MovieTicket类的源代码，违反了“开闭原则”，系统的灵活性
     * 和可扩展性较差。
     *
     * (3) 算法的复用性差，如果在另一个系统（如商场销售管理系统）中需要重用某些打折算法，只能通过对源代码进行复制粘贴
     * 来重用，无法单独重用其中的某个或某些算法（重用较为麻烦）。
     * */
    /**
     * @author：liwenhao
     * @Date:2017/2/18
     * @description:<p>测试客户端</p>
     * @version:v1.0
     */
    public static class Client {
        public static void main(String[] args) {
            MovieTicket mt = new MovieTicket();
            double originalPrice = 60.0; //原始票价
            double currentPrice; //折后价

            mt.setPrice(originalPrice);
            System.out.println("原始价为：" + originalPrice);
            System.out.println("---------------------------------");

            mt.setType("student"); //学生票
            currentPrice = mt.getPrice();
            System.out.println("折后价为：" + currentPrice);
            System.out.println("---------------------------------");

            mt.setType("children"); //儿童票
            currentPrice = mt.getPrice();
            System.out.println("折后价为：" + currentPrice);
        }
    }
}
