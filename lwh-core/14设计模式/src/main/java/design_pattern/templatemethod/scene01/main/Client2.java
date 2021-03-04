package design_pattern.templatemethod.scene01.main;

import design_pattern.templatemethod.scene01.model.HummerH2Model;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>客户开始使用这个模型</p>
 * @version:v1.0
 */
public class Client2 {
    public static void main(String[] args) {

        //客户开H2型号，出去玩耍了
        HummerH2Model h2 =new HummerH2Model();
        h2.run();

    }


    /**
     * 我们经常在使用，只是不知道这就是模板方法模式
     * 那些所谓的高手就可以很牛X的说：“用模板方法模
     * 式就可以实现”
     */
}
