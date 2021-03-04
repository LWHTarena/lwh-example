package design_pattern.proxy.scene02;

/**
 * 真正的生产红酒工厂
 */
public class RealRedWineFactory implements IRedWine {

    /**
     * 生产红酒工厂
     */
    @Override
    public void product() {
        System.out.println("红酒工厂生产红酒...");
    }

    /**
     * 销售红酒方法
     */
    @Override
    public void sell() {
        System.out.println("红酒工厂销售红酒...");
    }
}
