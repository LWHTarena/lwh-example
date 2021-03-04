package design_pattern.abstractfactory.scene01.info.impl;


import design_pattern.abstractfactory.scene01.info.Human;
import design_pattern.abstractfactory.scene01.info.util.HumanEnum;

/*
 * 女性 创建工厂
 */
public class FemaleHumanFactory extends AbstractHumanFactory {
	//创建一个男性黑种人
	@Override
	public Human createBlackHuman() {
		return super.createHuman(HumanEnum.BlackMaleHuman);
	}

	//创建一个白种人
	@Override
	public Human createWhiteHuman() {
		return super.createHuman(HumanEnum.WhiteMaleHuman);
	}

	//创建一个黄种人
	public Human createYellowHuman() {
		return super.createHuman(HumanEnum.YellowMaleHuman);
	}


}
