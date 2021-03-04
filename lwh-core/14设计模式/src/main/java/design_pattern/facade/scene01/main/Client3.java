package design_pattern.facade.scene01.main;

import design_pattern.facade.scene01.facade.ModenPostOffice2;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 门面模式【Facade Patter】
 * 开始给朋友写信了
 * </p>
 * @version:v1.0
 */
public class Client3 {
    public static void main(String[] args) {
        //现代化的邮局，有这项服务，邮局名叫Hell Road
        ModenPostOffice2 hellRoadPostOffice =
                new ModenPostOffice2();
        //现在你只要把信的内容和收信人的地址告诉她就行了
        String address ="海角7号，长寿街，2013-2014街";
        String context ="李文浩没空搭理你，你滚吧，有多远滚多远...";

        hellRoadPostOffice.sendLetter(context, address);
    }
    /**
     *看到没，客户简单了许多，提供这种模式后，系统的扩展性也有了
     *很大的提高
     *
     *这是一个很好的封装方法，一个子系统比较复杂的话，比如算法或者业务比较复杂
     *，就可以封装出一个门面或多个门面出来，项目的结构简单，而且扩展性非常好。
     *还有在一个比较大的项目中的时候，为了避免人员带来的风险，也可以使用这种模式，
     *技术水平比较差的成员，尽量安排独立的模块（Sub System）
     */
}
