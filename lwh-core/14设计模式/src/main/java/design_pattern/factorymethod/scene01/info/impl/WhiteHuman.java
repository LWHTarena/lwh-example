package design_pattern.factorymethod.scene01.info.impl;

import design_pattern.factorymethod.scene01.info.Human;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>白色人种</p>
 * @version:v1.0
 */
public class WhiteHuman implements Human {
    @Override
    public void cry() {
        System.out.println("白色人种会哭");

    }

    @Override
    public void laugh() {
        System.out.println("白色人种大笑，侵略的笑声！");

    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话，一般说的都是单字节");

    }
}
