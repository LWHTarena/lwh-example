package design_pattern.observer.scene01.main;


import design_pattern.observer.scene01.inter.impl.HanFeiZi;

/**
 * 这Client就是我们，用我们的视角看待这一段历史
 * @author liwenhao
 *
 */
public class Client {

	public static void main(String[] args) throws InterruptedException{
		//定义出韩非子
		HanFeiZi hanFeiZi =new HanFeiZi();

		//然后这里我们看看韩非子在干什么
		hanFeiZi.havaBreakfast();

		//韩非子娱乐了
		hanFeiZi.havaFun();
	}
}
