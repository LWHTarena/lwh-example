package design_pattern.abstractfactory.scene01.info.impl;


import design_pattern.abstractfactory.scene01.info.Human;
import design_pattern.abstractfactory.scene01.info.util.HumanEnum;

/**
 *  * 男性 创建工厂
 */
public class MaleHumanFactory extends AbstractHumanFactory {
	//创建一个女性黑种人
	@Override
	public Human createBlackHuman() {
		return super.createHuman(HumanEnum.BlackFemaleHuman);
	}

	//创建一个女白种人
	@Override
	public Human createWhiteHuman() {
		return super.createHuman(HumanEnum.whiteFemaleHuman);
	}

	//创建一个女黄种人
	public Human createYellowHuman() {
		return super.createHuman(HumanEnum.YellowFeMaleHuman);
	}
}
