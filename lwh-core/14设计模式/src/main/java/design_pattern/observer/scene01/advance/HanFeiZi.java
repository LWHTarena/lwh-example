package design_pattern.observer.scene01.advance;

import java.util.ArrayList;
/**
 * 观察者模式【Observer Pattern】
 * @author liwenhao
 *
 */
public class HanFeiZi implements Observable {

	//定义个变成数组，存放所有的观察者
	private ArrayList<Observer> observerList =
			new ArrayList<Observer>();

	//增加观察者
	public void addObserver(Observer observer) {
		this.observerList.add(observer);

	}

	//删除观察者
	public void deleteObserver(Observer observer) {
		this.observerList.remove(observer);
	}

	//通知所有的观察者
	public void notifyObservers(String context) {
		for(Observer observer:observerList){
			observer.update(context);
		}

	}

	//韩非子要吃饭了
	public void havaBreakfast(){
		System.out.println("HanFeiZi.havaBreakfast()...韩非子：开始吃饭了");
		this.notifyObservers("韩非子在吃饭");
	}

	//韩非子在娱乐
	public void haveFun(){
		System.out.println("HanFeiZi.haveFun()...韩非子：开始娱乐了");
		this.notifyObservers("韩非子在娱乐");
	}

}
