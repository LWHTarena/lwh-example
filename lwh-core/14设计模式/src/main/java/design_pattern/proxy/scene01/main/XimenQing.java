package design_pattern.proxy.scene01.main;

import design_pattern.proxy.scene01.proxy.impl.WangPo;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>定义一个西门庆，这人色中恶鬼</p>
 * @version:v1.0
 */
public class XimenQing {
    	/*
	 * 水浒里是这样写的：西门庆被潘金莲用竹竿敲了一下，痴迷了
	 * 被王婆看到，就开始撮合两人好事，王婆作为潘金莲的代理人
	 * 收了不少好处费，那我们假设一下：
	 * 如果没有王婆在中间牵线，这两个不要脸的能成吗？难说的很！
	 */

    public static void main(String[] args) {
        //把王婆叫出来
        WangPo wangPo =new WangPo();

        //然后西门庆就说，我要和潘金莲happy，然后就安排了西门庆丢筷子的那出戏
        wangPo.makeEyeWithMan();//看到没，虽然表面上是王婆在做，实际上爽的是潘金莲
        wangPo.happyWithMan();

    }
    /**
     * 通过代理人实现了某种目的，如果去掉王婆这个中间环节，直接是西门庆和潘金莲勾搭，估计很难成就武松杀嫂事件
     */
}
