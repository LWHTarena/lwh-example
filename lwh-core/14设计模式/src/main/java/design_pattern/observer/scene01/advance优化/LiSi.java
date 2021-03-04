package design_pattern.observer.scene01.advance优化;

import java.util.Observable;
import java.util.Observer;

public class LiSi implements Observer {

	//首先李斯是个观察者，一旦韩非子有活动，她就知道，她就要向老板汇报
	@Override
	public void update(Observable observable,Object obj) {
		System.out.println("李斯：观察到韩非子活动，开始想老板汇报...");
		this.reportToQinShiHuang(obj.toString());
		System.out.println("李斯：汇报完毕，秦老板赏给他们两个萝卜吃吃...\n");

	}

	private void reportToQinShiHuang(String reportContext) {
		System.out.println("李斯：报告，秦老板！韩非子有活动了--->"+reportContext);
	}

}
