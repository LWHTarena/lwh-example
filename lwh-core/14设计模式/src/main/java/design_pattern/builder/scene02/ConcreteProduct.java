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
public class ConcreteProduct extends Builder {
    private Product product  =new Product();


    /**
     * 设置昌平零件
     */
    @Override
    public void setPart() {
        /**
         * 产品类内的逻辑处理
         */
    }

    /**
     * 组件一个产品
     * @return
     */
    @Override
    public Product buildProduct() {
        return product;
    }
}
