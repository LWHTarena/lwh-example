package design_pattern.facade.scene01.facade;

import design_pattern.facade.scene01.inter.LetterProcess;
import design_pattern.facade.scene01.inter.impl.LetterProcessImpl;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 这个类说什么意思？
 * 		就是说现在有一个叫Hell Road PostOffice（地狱路邮局）提供了一种新型的服务
 * ，客户只要把信的内容以及收信地址给他们，他们就会把信写好，封好，病发送出去。这种
 * 服务提出减少了客户的工作
 * @author liwenhao
 * 增加了安全监察步骤
 * </p>
 * @version:v1.0
 */
public class ModenPostOffice2 {
    private LetterProcess letterProcess =
            new LetterProcessImpl();
    private Police letterPolice =new Police();
    //写信，封转，邮递，一体化
    public void sendLetter(String context,String address){
        //帮你写信
        letterProcess.writeContext(context);
        //写好信封

        //警察要检查信件了
        letterPolice.checkLetter(letterProcess);
        letterProcess.fillEnvelope(address);
        //把信放到信封中
        letterProcess.letterInotoEnvelope();
        //邮递信件
        letterProcess.sendLetter();
    }
}
