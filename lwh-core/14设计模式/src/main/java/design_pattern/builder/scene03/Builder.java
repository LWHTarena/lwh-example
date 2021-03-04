package design_pattern.builder.scene03;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：定义组装的过程（Builder）：组装电脑的过程</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public abstract class Builder {

    /**
     * 第一步：装CPU
     * 声明为抽象方法，具体由子类实现
     */
    public abstract void BuildCPU();

    /**
     * 第二步：装主板
     * 声明为抽象方法，具体由子类实现
     */
    public abstract void BuildMainboard();

    /**
     * 第三步：装硬盘
     * 声明为抽象方法，具体由子类实现
     */
    public abstract void BuildHD();

    /**
     * 返回产品的方法：获得组装好的电脑
     * @return
     */
    public abstract Computer GetComputer();

}
