package design_pattern.proxy.scene01.proxy.impl;

import design_pattern.proxy.scene01.proxy.KindWomen;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 卢俊义的老婆贾氏（就是和那个固管家荀合）
 * 那我们也让王婆做她的代理
 * </p>
 * @version:v1.0
 */
public class JiaShi implements KindWomen {
    @Override
    public void happyWithMan() {
        System.out.println("贾氏正在happy中......");
    }

    @Override
    public void makeEyeWithMan() {
        System.out.println("贾氏抛媚眼");
    }
}
