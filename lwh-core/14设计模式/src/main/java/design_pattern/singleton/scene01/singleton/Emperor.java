package design_pattern.singleton.scene01.singleton;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 单例模式【Singleton Pattern】
 * 中国的历史上一般都是朝代一个皇帝，有两个皇帝的话，必然压迫PK出一个皇帝出来
 * </p>
 * @version:v1.0
 */
public class Emperor {
    private static Emperor emperor =null;//定义一个皇帝在那里，然后给这个皇帝名字

    private Emperor(){
        //世俗和道德约束你，目的就是不让你产生第二个皇帝
    }

    public static Emperor getInstance(){//如果皇帝还没有定义，哪就定义一个
        if(emperor ==null){
            emperor =new Emperor();
        }
        return emperor;
    }

    //皇帝叫什么名字呀？
    public static void emperorInfo(){
        System.out.println("我就是皇帝某某某...");
    }
    //这就是一个单例模式
}
