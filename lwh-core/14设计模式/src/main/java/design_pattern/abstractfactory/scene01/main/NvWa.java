package design_pattern.abstractfactory.scene01.main;

import design_pattern.abstractfactory.scene01.info.Human;
import design_pattern.abstractfactory.scene01.info.HumanFactory;
import design_pattern.abstractfactory.scene01.info.impl.FemaleHumanFactory;
import design_pattern.abstractfactory.scene01.info.impl.MaleHumanFactory;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 女娲建立起了两条生产线，分别是：
 * 男性生产线
 * 女性生产线
 * </p>
 * @version:v1.0
 */
public class NvWa {
    public static void main(String[] args){
        //第一条生产线，男性生产线
        HumanFactory maleHumanFactory =
                new MaleHumanFactory();
        //第二条生产线，女性生产线
        HumanFactory femaleHumanFactory =
                new FemaleHumanFactory();

        //生产线建立完毕，开始生产人了
        Human maleYellowHuman =
                maleHumanFactory.createYellowHuman();
        Human femaleYellowHuman =
                femaleHumanFactory.createYellowHuman();
        maleYellowHuman.cry();
        maleYellowHuman.laugh();
        femaleYellowHuman.sex();
        femaleYellowHuman.talk();

    }
}
