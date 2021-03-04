package design_pattern.observer.scene01.advance优化;

import java.util.Observable;;
/**
 * 观察者模式【Observer Pattern】
 * @author liwenhao
 *
 */
public class HanFeiZi extends Observable {

	//韩非子要吃饭了
	public void havaBreakfast(){
		System.out.println("HanFeiZi.havaBreakfast()...韩非子：开始吃饭了");
		//通知所有的观察者
		super.setChanged();
		super.notifyObservers("韩非子在吃饭");
	}

	//韩非子在娱乐
	public void haveFun(){
		System.out.println("HanFeiZi.haveFun()...韩非子：开始娱乐了");
		super.setChanged();
		super.notifyObservers("韩非子在娱乐");
	}

}
