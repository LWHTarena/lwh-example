package design_pattern.builder.scene02;

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
public abstract class Builder {
    //设置产品的不同部分， 以获得不同的产品
    public abstract void setPart();
    //建造产品
    public abstract Product buildProduct();
}
