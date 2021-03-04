package design_pattern.templatemethod.scene01.main;

import design_pattern.templatemethod.scene01.model.HummerH1Model;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>客户开始使用这个模型</p>
 * @version:v1.0
 */
public class Client3 {

    public static void main(String[] args) {
        //客户开着H1型号，出去遛弯了
        HummerH1Model h1 =new HummerH1Model();
        h1.setAlarm(false);
        h1.run();

    }


    /**
     * 我们经常在使用，只是不知道这就是模板方法模式
     * 那些所谓的高手就可以很牛X的说：“用模板方法模
     * 式就可以实现”
     *
     * 总结一下：模板方法模式就是在模板方法中按照一个
     * 规则和顺序调用基本方法
     * 模板方法在一些开源框架应用很多，她提供了一个抽象类
     * ，然后开源框架写了一堆子类。如果你要扩展功能，可以
     * 继承了这个抽象类，然后修改protected方法，再然后就是
     * 调用一个类似execte（）方法，就完成你的扩展开发，确实是一种简单模式。
     */
}
