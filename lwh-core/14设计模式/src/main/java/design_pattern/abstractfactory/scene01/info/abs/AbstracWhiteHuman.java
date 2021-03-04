package design_pattern.abstractfactory.scene01.info.abs;

import design_pattern.abstractfactory.scene01.info.Human;

/**
 * @author liwenhao
 */
public abstract class AbstracWhiteHuman implements Human {

	@Override
	public void cry() {
		System.out.println("白色人种会哭");

	}

	@Override
	public void laugh() {
		System.out.println("白色人种会大笑，侵略的笑！");

	}

	@Override
	public void talk() {
		System.out.println("白色人种会说话，一般都是单字节！");

	}

}
