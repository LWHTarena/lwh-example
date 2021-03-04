package design_pattern.singleton.scene01.main;

import design_pattern.singleton.scene01.singleton.Emperor;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 大臣是天天要见皇帝，今天见得皇帝和昨天
 * 的、前天不一样那就出问题了！
 * </p>
 * @version:v1.0
 */
public class Minister {

    public static void main(String[] args) {
        //第一天
        Emperor emperor1 =Emperor.getInstance();
        emperor1.emperorInfo();//第一天见的皇帝叫什么名字呢？

        //第二天
        Emperor emperor2 =Emperor.getInstance();
        Emperor.emperorInfo();

        //第三天
        Emperor emperor3 =Emperor.getInstance();
        emperor2.emperorInfo();
        //三天见皇帝都是同一个人，荣幸吧！

    }

    /**
     * 单例模式很简单，就是在构造函数中多加了一个构造函数，访问权限是private
     * 就可以了
     * 但是会有风险？
     * 		在一个B/S项目中，每一个HTTP Reques 请求到J2EE的容器上都
     * 创建了一个线程，每一个线程都要创建一个单例对象
     */
}
