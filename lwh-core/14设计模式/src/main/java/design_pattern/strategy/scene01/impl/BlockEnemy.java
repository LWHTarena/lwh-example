package design_pattern.strategy.scene01.impl;

import design_pattern.strategy.scene01.IStrategy;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>孙夫人断后，挡住追兵</p>
 * @version:v1.0
 */
public class BlockEnemy implements IStrategy {
    @Override
    public void operate() {
        System.out.println("孙夫人断后，挡住追兵");
    }
}
