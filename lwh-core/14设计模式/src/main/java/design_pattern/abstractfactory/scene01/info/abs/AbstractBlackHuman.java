package design_pattern.abstractfactory.scene01.info.abs;

import design_pattern.abstractfactory.scene01.info.Human;

/**
 * @author liwenhao
 *
 * 为什么要修改成抽象类呢？要定义性别呀
 */
public abstract class AbstractBlackHuman implements Human {

	@Override
	public void cry() {
		System.out.println("黑人会哭");

	}

	@Override
	public void laugh() {
		System.out.println("黑人大笑，幸福呀！");

	}

	@Override
	public void talk() {
		System.out.println("黑人可以说话，一般人听不懂！");

	}

}
