package design_pattern.abstractfactory.scene01.info;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 抽象工厂模式【Abstract Factory Pattern】
 *
 * 这次顶一个接口，应该要造不同性别的人，需要不同的生产线
 * 那这个八卦炉（工厂）必须可以制造男人和女人
 * </p>
 * @version:v1.0
 */
public interface HumanFactory {
    //制造黄色人种
    public Human createYellowHuman();

    //制造一个白色人种
    public Human createWhiteHuman();

    //制造一个黑人
    public Human createBlackHuman();
}
