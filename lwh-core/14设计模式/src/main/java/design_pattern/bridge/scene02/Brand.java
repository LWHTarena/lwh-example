package design_pattern.bridge.scene02;

/**
 * Created by Administrator on 2017/2/25.
 * <p>品牌</p>
 */
public interface Brand {
    void sale();
}

class Lenovo implements Brand{
    @Override
    public void sale() {
        System.out.println("销售联想电脑");
    }
}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售Dell电脑");
    }

}

class Shenzhou implements Brand {

    @Override
    public void sale() {
        System.out.println("销售神舟电脑");
    }

}
