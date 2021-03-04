package design_pattern.observer.scene01.inter.impl;

import design_pattern.observer.scene01.inter.IHanFeiZi;
import design_pattern.observer.scene01.inter.ILiSi;

/**
 * 韩非子，李斯的师弟，韩国的重要人物
 * @author liwenhao
 *
 */

public class HanFeiZi implements IHanFeiZi {

	//把李斯声明出来
	private ILiSi liSi =new LiSi();

	//韩非子要吃饭了

	@Override
	public void havaBreakfast() {//韩非子要吃饭了
		System.out.println("HanFeiZi.havaBreakfast()...韩非子：开始吃饭了");
		this.liSi.update("韩非子在吃饭");
	}

	@Override
	public void havaFun() {
		System.out.println("HanFeiZi.havaFun()...韩非子：开始娱乐了");
		this.liSi.update("韩非子在娱乐");
	}

}

