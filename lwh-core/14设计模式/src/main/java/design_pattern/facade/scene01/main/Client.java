package design_pattern.facade.scene01.main;

import design_pattern.facade.scene01.inter.LetterProcess;
import design_pattern.facade.scene01.inter.impl.LetterProcessImpl;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p> 开始给朋友写信了</p>
 * @version:v1.0
 */
public class Client {
    public static void main(String[] args) {
        //创建一个处理写信的过程
        LetterProcess letterProcess =
                new LetterProcessImpl();

        //开始写信了
        letterProcess.writeContext("李文浩没空搭理你，你滚吧，有多远滚多远...");

        //开始写信封
        letterProcess.fillEnvelope("海角7号，长寿街，2013-2014街");

        //把信封放到信封里，病封装好
        letterProcess.letterInotoEnvelope();

        //跑到邮局把信件塞到邮箱，投递
        letterProcess.sendLetter();
    }
    /**
     * 		这个过程高内聚，离要求相差甚远
     * 如果这四个步骤顺序出错，信就不能邮寄出去
     * 那怎么改进呢？
     */
}
