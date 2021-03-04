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
public class Director {

    Builder builder =new ConcreteBuilder();

    public Director() {
        builder. buildCPU();
        builder.buildMainboard();
        builder. buildHD();
    }

    public Computer getComputer(){
        return builder.getComputer();
    }
}
