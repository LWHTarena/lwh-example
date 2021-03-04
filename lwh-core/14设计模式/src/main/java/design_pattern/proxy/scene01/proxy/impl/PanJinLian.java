package design_pattern.proxy.scene01.proxy.impl;

import design_pattern.proxy.scene01.proxy.KindWomen;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 定义： 潘金莲是一个说明样的人
 * </p>
 * @version:v1.0
 */
public class PanJinLian implements KindWomen {
    @Override
    public void makeEyeWithMan() {
        System.out.println("潘金莲抛媚眼");

    }

    @Override
    public void happyWithMan() {
        System.out.println("潘金莲在和男人做那个......");

    }

}
