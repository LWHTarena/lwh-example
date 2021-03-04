package design_pattern.factorymethod.scene01.info.impl;

import design_pattern.factorymethod.scene01.info.Human;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>黄色人种</p>
 * @version:v1.0
 */
public class YellowHuman implements Human {

    @Override
    public void cry() {
        System.out.println("黄色人种会哭");

    }

    @Override
    public void laugh() {
        System.out.println("黄色人种大笑，幸福呀！");

    }

    @Override
    public void talk() {
        System.out.println("黄色人种会说话，一般说的都是双字节");

    }
}
