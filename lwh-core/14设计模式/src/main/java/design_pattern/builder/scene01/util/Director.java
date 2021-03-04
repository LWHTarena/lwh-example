package design_pattern.builder.scene01.util;

import design_pattern.builder.scene01.model.BMWBuilder;
import design_pattern.builder.scene01.model.BMWModel;
import design_pattern.builder.scene01.model.BenzBuilder;
import design_pattern.builder.scene01.model.BenzModel;

import java.util.ArrayList;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 就像导演安排演员一样，一切听从指挥
 *
 *导演安排顺序，生产车辆模型
 * </p>
 * @version:v1.0
 */
public class Director {
    private ArrayList<String> sequence =new ArrayList<String>();
    private BenzBuilder benzBuilder =new BenzBuilder();
    private BMWBuilder bmwBuilder =new BMWBuilder();

    /*
     * A类型的奔驰模型，先start后stop，其他什么引擎的，喇叭的一概没有
     */
    public BenzModel getABenzModel(){
        //清理场景，这里是一些初级程序员不注意的地方
        this.sequence.clear();

        //这辆奔驰模型执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");

        //按照顺序返回一辆奔驰
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    /*
     * B型号的奔驰模型，先发动引擎，然后启动，然后停止，没有喇叭
     *
     */
    public BenzModel getBBenzModel(){
        this.sequence.clear();

        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");

        this.benzBuilder.setSequence(sequence);
        return (BenzModel) this.benzBuilder.getCarModel();

    }

    /*
     * C型号的宝马车是先按下喇叭（炫耀嘛），然后启动，然后停止
     */
    public BMWModel getCBMWModel(){
        this.sequence.clear();

        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");

        this.bmwBuilder.setSequence(sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    /*
     * D类型的宝马车之友一个功能，就是跑，启动起来就跑，永远不停止，牛叉
     */
    public BMWModel getDBMWModel(){
        this.sequence.clear();

        this.sequence.add("start");

        this.bmwBuilder.setSequence(sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }
    /**
     * .....还可以有很多....
     *
     * this.sequence.clear() --作为一个系统分析师或是技术经理一定要告诉项
     * 目成员，ArrayList 和HashMap如果定义成类的成员变量，那你在方法中调用
     * 一定要做一个clear的动作，防止数据混乱。
     *
     */
}
