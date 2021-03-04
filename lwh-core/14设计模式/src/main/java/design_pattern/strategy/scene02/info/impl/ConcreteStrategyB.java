package design_pattern.strategy.scene02.info.impl;

import design_pattern.strategy.scene02.info.AbstractStrategy;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>封装每一种具体算法的类作为该抽象策略类的子类</p>
 * @version:v1.0
 */
public class ConcreteStrategyB extends AbstractStrategy {
    @Override
    public void algorithm() {
        System.out.println("算法B");
    }
}
