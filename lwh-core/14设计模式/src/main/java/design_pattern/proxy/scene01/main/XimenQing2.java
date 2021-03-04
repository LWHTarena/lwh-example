package design_pattern.proxy.scene01.main;

import design_pattern.proxy.scene01.proxy.impl.JiaShi;
import design_pattern.proxy.scene01.proxy.impl.WangPo;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>定义一个西门庆，这人色中恶鬼，然他勾搭贾氏</p>
 * @version:v1.0
 */
public class XimenQing2 {

    public static void main(String[] args) {
        //改编一下历史，贾氏被西门庆勾走
        JiaShi jiaShi =new JiaShi();
        WangPo wangPo =new WangPo(jiaShi);//让王婆作为贾氏的代理人

        wangPo.makeEyeWithMan();
        wangPo.happyWithMan();

    }
    /**
     * 通过代理人实现了某种目的，如果去掉王婆这个中间环节，直接是西门庆和潘金莲勾搭，估计很难成
     * 就武松杀嫂事件
     *
     * 总结一：
     * 代理模式主要用了Java的多态，干活的是代理类，代理类主要是接活
     * *代理类和被代理类必须同一个接口*
     */
}
