package design_pattern.observer.scene01.inter.impl;

import design_pattern.observer.scene01.inter.ILiSi;

/**
 * 李斯这个人，是个观察者，只要韩非子一有动静，这边就知道
 * @author liwenhao
 *
 */
public class LiSi implements ILiSi {

	//首先李斯是个观察者，一旦韩非子有活动，他就知道，她就要像老板汇报
	@Override
	public void update(String str) {
		System.out.println("李斯：观察到韩非子活动，开始想老板汇报...");
		this.reportToQinShiHuang(str);
		System.out.println("李斯：汇报完毕，秦老板赏给他们两个萝卜吃吃...");
	}

	private void reportToQinShiHuang(String reportContext) {
		System.out.println("李斯：报告，秦老板！韩非子有活动了--->"+reportContext);

	}

}