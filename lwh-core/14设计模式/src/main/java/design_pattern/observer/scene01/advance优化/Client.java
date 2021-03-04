package design_pattern.observer.scene01.advance优化;

import java.util.Observer;

public class Client {

	public static void main(String[] args) {
		//三个观察者
		Observer liSi =new LiSi();
		Observer liWenhao =new LiWenhao();
		Observer liuSi =new LiuSi();

		//定义韩非子
		HanFeiZi hanFeiZi =new HanFeiZi();

		//我们后人根据历史，描述这个场景，有三个人在观察韩非子
		hanFeiZi.addObserver(liSi);
		hanFeiZi.addObserver(liWenhao);
		hanFeiZi.addObserver(liuSi);

		//然后这里我们看看韩非子在干什么？
		hanFeiZi.havaBreakfast();

	}


	/**观察者模式在实际项目的应用中非常常见，比如你到ATM机器上取钱，多次输入错误密码，卡就
	 * 会被ATM吞掉，听你卡动作发生的时候，会触发哪些事件？第一摄像头连续快拍；第二，通知监
	 * 控系统，吞卡发生；第三初始化ATM机屏幕，返回最初状态，你不能因为就吞掉了一张卡，整个
	 * ATM都不能用了吧，一般前两个动作都是通过观察者模式来完成的。
	 *
	 * 观察者模式【Observer Pattern】有一个变种叫做发布/订阅模式【Publish/Subscribe】，EJB(
	 * Enterprise JavaBean)的开发，这个你绝对不会陌生.
	 *
	 * 什么情况下使用呢？观察者可以实现消息的广播，一个消息可以出发多个时间，这是观察者模式非
	 * 常重要的功能。
	 */
}
