package design_pattern.abstractfactory.scene01.info.impl;

import design_pattern.abstractfactory.scene01.info.Human;
import design_pattern.abstractfactory.scene01.info.HumanFactory;
import design_pattern.abstractfactory.scene01.info.util.HumanEnum;

/**
 * 编写一个抽象类，根据enum创建一个人类出来
 * @author liwenhao
 *
 */
public abstract class AbstractHumanFactory implements HumanFactory {
	/*
     * 给定一个性别人类，创建一个人类出来  专业术语是产生产品等级
     * @see com.tarena.infa.HumanFactory#createBlackHuman()
     */
	protected Human createHuman(HumanEnum humanEnum) {
		Human human =null;

		//如果传递进来不是以个enum中具体的以element的话，则不处理
		if(!humanEnum.getValue().equals("")){
			try {//直接产生一个实例
				human =
						(Human) Class.forName(humanEnum.getValue()).newInstance();
			} catch (Exception e) {
				//因为使用了enum，这中异常情况不会产生了，除非你的enum有问题
				e.printStackTrace();
			}
		}
		return human;
	}

	/**
	 * 这就是引入enum的好处，createHuman(HumanEnum humanEnum)这个
	 * 方法定义了输入参数必须是HumanEnum 类型，然后直接使用human.getValue()方法
	 * 就能获得具体传递进来的值
	 *
	 * 这个抽象类的目的就是减少下边实现类的代码量
	 */

}
