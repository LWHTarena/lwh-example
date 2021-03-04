package design_pattern.facade.scene01.inter.impl;

import design_pattern.facade.scene01.inter.LetterProcess;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p> 写信的具体实现</p>
 * @version:v1.0
 */
public class LetterProcessImpl implements LetterProcess {
    //写信
    @Override
    public void writeContext(String context) {
        System.out.println("填写信的具体内容..."+context);

    }

    //在信封上填写必要信息
    @Override
    public void fillEnvelope(String address) {
        System.out.println("填写收件人地址及姓名..."+address);
    }

    //把信封放到信封中，并封好
    @Override
    public void letterInotoEnvelope() {
        System.out.println("把信放到信封中...");
    }

    //塞到邮箱中，邮递
    public void sendLetter() {
        System.out.println("邮递信件...");
    }
}
