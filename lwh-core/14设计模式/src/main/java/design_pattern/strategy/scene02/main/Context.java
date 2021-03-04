package design_pattern.strategy.scene02.main;

import design_pattern.strategy.scene02.info.AbstractStrategy;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>对于Context类而言，在它与抽象策略类之间建立一个关联关系</p>
 * @version:v1.0
 */
public class Context {
    private AbstractStrategy strategy; //维持一个对抽象策略类的引用

    public void setStrategy(AbstractStrategy strategy) {
        this.strategy = strategy;
    }

    //调用策略类中的算法
    public void algorithm() {
        strategy.algorithm();
    }
}
