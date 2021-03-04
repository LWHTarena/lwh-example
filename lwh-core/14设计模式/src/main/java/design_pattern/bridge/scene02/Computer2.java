package design_pattern.bridge.scene02;

/**
 * Created by Administrator on 2017/2/25.
 * <p>电脑类型的维度</p>
 */
public class Computer2 {

    protected Brand brand;

    public Computer2(Brand brand) {
        this.brand = brand;
    }
    public void sale(){
        brand.sale();
    }
}

class Desktop2 extends Computer2 {

    public Desktop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售台式机");
    }
}

class Laptop2 extends Computer2 {

    public Laptop2(Brand b) {
        super(b);
    }

    @Override
    public void sale() {
        super.sale();
        System.out.println("销售笔记本");
    }
}

