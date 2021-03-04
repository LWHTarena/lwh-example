package design_pattern.state.scene02.inter.impl;

import design_pattern.state.scene02.inter.State;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>已入住状态</p>
 * @version:v1.0
 */
public class CheckedInState implements State {
    @Override
    public void handler() {
        System.out.println("房间已入住！请勿打扰！");
    }
}
