package design_pattern.state.scene02.inter.impl;

import design_pattern.state.scene02.inter.State;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>已预订状态</p>
 * @version:v1.0
 */
public class BookedState implements State {
    @Override
    public void handler() {
        System.out.println("房间已预订！别人不能定！");
    }
}
