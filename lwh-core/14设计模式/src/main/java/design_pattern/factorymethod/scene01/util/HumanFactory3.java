package design_pattern.factorymethod.scene01.util;

import design_pattern.factorymethod.scene01.info.Human;

import java.util.HashMap;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 工厂方法模式【Factory Method Pattern】
 * 工厂模式还有一个非常重要的应用，就是延迟初始化（Lazy initiallization）
 * 什么是延迟初始化？
 * 		一个对象初始化完毕后 就不释放，等到再次用到得就不会再次初始化了，
 * 直接从内存中拿到就可以了.
 *
 * 这个在初始化很消耗的情况下比较实用，比如你连接硬件，或者是
 * 为了初始化一个类需要准备比较多条件（参数），通过这种方式可以
 * 很好的减少项目的复杂程度
 * </p>
 * @version:v1.0
 */
public class HumanFactory3 {
    //定义一个map，初始化过的Human对象都放在这里
    public static HashMap<String,Human> humans =
            new HashMap<String, Human>();

    //定义一个烤箱，泥巴塞进去，人就出来，这个也太先进了
    public static Human createHuman(Class c){
        Human human =null;//定义一个类型的人类
        try {
            //如果Map中有，则直接从取出，不用初始化了
            if(humans.containsKey(c.getSimpleName())){
                human =humans.get(c.getSimpleName());
            }else{
                human =(Human) Class.forName(c.getName()).newInstance();
                //放到map中
                humans.put(c.getSimpleName(), human);
            }

        } catch (InstantiationException e) {//你要是不说个人类颜色的话，没法烤
            System.out.println("必须指定人类的颜色");
        } catch (IllegalAccessException e) {//一定定义的人类有问题，那就烤不出来了，这是。。。
            System.out.println("人类定义错误！");
        } catch (ClassNotFoundException e) {//你随便说个人类，我到哪里给你制造出来？！
            System.out.println("混蛋，你指定的人类找不到！");
        }
        return human;
    }
}
