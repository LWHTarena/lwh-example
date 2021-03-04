package design_pattern.strategy.scene02.main;

import design_pattern.strategy.scene02.info.AbstractStrategy;
import design_pattern.strategy.scene02.info.impl.ConcreteStrategyA;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class Client {
    public static void main(String[] args) {
        Context context = new Context();
        AbstractStrategy strategy;
        strategy = new ConcreteStrategyA(); //可在运行时指定类型
        context.setStrategy(strategy);
        context.algorithm();
    }
}
