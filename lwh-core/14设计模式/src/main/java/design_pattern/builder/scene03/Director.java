package design_pattern.builder.scene03;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述： 电脑城老板委派任务给装机人员（Director）</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class Director {


    /**
     * 指挥装机人员组装电脑
     * @param builder
     */
    public void Construct(Builder builder){
        builder. BuildCPU();
        builder.BuildMainboard();
        builder. BuildHD();
    }
}
