package design_pattern.strategy.scene01;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>策略类</p>
 * @version:v1.0
 */
public class Context {

    public Context() {
    }

    /**
     * 构造函数，你要使用哪个妙计
     */
    private IStrategy straegy;

    public Context(IStrategy straegy){
        this.straegy =straegy;
    }

    /**
     * 使用计谋了，看我出招了
     */
    public void operate(){
        this.straegy.operate();
    }
}
