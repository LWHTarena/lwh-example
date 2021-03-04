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
public class ConcreteBuilder implements Builder {

    /**通过聚合**/
    Computer computer =new Computer();


    @Override
    public void buildCPU() {
        computer.add("组装CPU");
    }

    @Override
    public void buildMainboard() {
        computer.add("组装主板");
    }

    @Override
    public void buildHD() {
        computer.add("组装主板");
    }

    @Override
    public Computer getComputer() {
        return computer;
    }
}
