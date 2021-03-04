package design_pattern.builder.scene03;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：创建具体的建造者（ConcreteBuilder）:装机人员</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class ConcreteBuilder extends Builder{
    /**
     * 创建产品实例
     */
    Computer computer = new Computer();

    /**
     * 组装产品
     */
    @Override
    public void  BuildCPU(){
        computer.Add("组装CPU");
    }

    @Override
    public void  BuildMainboard(){
        computer.Add("组装主板");
    }

    @Override
    public void  BuildHD(){
        computer.Add("组装主板");
    }

    /**
     * 返回组装成功的电脑
     * @return
     */
    @Override
    public Computer GetComputer(){
        return computer;
    }
}
