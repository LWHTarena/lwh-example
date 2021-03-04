package design_pattern.builder.scene04;

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
public interface Builder {

    public void buildCPU();

    public void buildMainboard();

    public void buildHD();

    /**
     * 返回产品的方法：获得组装好的电脑
     * @return
     */
    public Computer getComputer();

}
