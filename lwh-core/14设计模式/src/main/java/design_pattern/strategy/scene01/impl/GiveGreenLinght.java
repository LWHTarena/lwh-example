package design_pattern.strategy.scene01.impl;

import design_pattern.strategy.scene01.IStrategy;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>求吴国人开绿灯</p>
 * @version:v1.0
 */
public class GiveGreenLinght implements IStrategy {
    @Override
    public void operate() {
        System.out.println("求吴国太开个绿灯，放行");
    }
}
