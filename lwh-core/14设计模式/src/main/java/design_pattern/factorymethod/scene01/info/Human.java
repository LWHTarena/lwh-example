package design_pattern.factorymethod.scene01.info;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 工厂方法模式【Factory Method Pattern】
 * 定义一个人类的统称
 * </p>
 * @version:v1.0
 */
public interface Human {
    //首先定义什么事人类

    //人是愉快的，会笑的，本来是想用smile表示，想了一下laugh更合适，好长时间没有大笑了
    public void laugh();

    //人来还会哭，代表痛苦
    public void cry();

    //人类会说话
    public void talk();
}
