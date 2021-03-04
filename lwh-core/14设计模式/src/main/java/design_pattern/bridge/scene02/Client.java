package design_pattern.bridge.scene02;

/**
 * Created by Administrator on 2017/2/25.
 */
public class Client {

    public static void main(String[] args) {
        //销售联想的笔记本电脑
        Computer2  c = new Laptop2(new Lenovo());
        c.sale();

        //销售神舟的台式机
        Computer2 c2 = new Desktop2(new Shenzhou());
        c2.sale();


    }
}
