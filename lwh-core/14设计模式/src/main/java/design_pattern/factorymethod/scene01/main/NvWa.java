package design_pattern.factorymethod.scene01.main;

import design_pattern.factorymethod.scene01.info.Human;
import design_pattern.factorymethod.scene01.info.impl.BlackHuman;
import design_pattern.factorymethod.scene01.info.impl.WhiteHuman;
import design_pattern.factorymethod.scene01.info.impl.YellowHuman;
import design_pattern.factorymethod.scene01.util.HumanFactory;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p></p>
 * @version:v1.0
 */
public class NvWa {
    public static void main(String[] args) {
        //女娲第一次造人，实验性质，少造点，火候不足，缺陷产品
        System.out.println("-------造出的第一批人是这样的：白人-----");
        Human whiteHuman =
                HumanFactory.createHuman(WhiteHuman.class);
        whiteHuman.cry();
        whiteHuman.laugh();
        whiteHuman.talk();

        //女娲第二次造人，火候加足点，然后又出了个次品，黑人
        System.out.println("-------造出的第二批人是这样的：黑人-----");
        Human blackHuman =HumanFactory.createHuman(BlackHuman.class);
        blackHuman.cry();
        blackHuman.laugh();
        blackHuman.talk();

        //第三批人了，这次火候掌握的正好，黄色人种
        System.out.println("-------造出的第二批人是这样的：黄色人种-----");
        Human yellowHuman =HumanFactory.createHuman(YellowHuman.class);
        yellowHuman.cry();
        yellowHuman.laugh();
        yellowHuman.talk();
    }
}
