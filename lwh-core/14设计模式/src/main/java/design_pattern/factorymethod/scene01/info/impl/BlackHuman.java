package design_pattern.factorymethod.scene01.info.impl;

import design_pattern.factorymethod.scene01.info.Human;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>黑色人种</p>
 * @version:v1.0
 */
public class BlackHuman implements Human {
    @Override
    public void cry() {
        System.out.println("黑人会哭");

    }

    @Override
    public void laugh() {
        System.out.println("黑人会笑！");

    }

    @Override
    public void talk() {
        System.out.println("黑人可以说话，一般人听不懂");

    }
}
