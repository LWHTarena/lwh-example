package design_pattern.factorymethod.scene01.util;

import design_pattern.factorymethod.scene01.info.Human;

import java.util.List;
import java.util.Random;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 工厂方法模式【Factory Method Pattern】
 * 修改HumanFactory.java,增加了createHuman()方法
 * </p>
 * @version:v1.0
 */
public class HumanFactory2 {
    //定义一个烤箱，泥巴塞进去，人就出来，这个也太先进了
    public static Human createHuman(Class c){
        Human human =null;//定义一个类型的人类
        try {
            human =(Human)Class.forName(c.getName()).newInstance();
        } catch (InstantiationException e) {
            System.out.println("必须指定人类的颜色");
        } catch (IllegalAccessException e) {
            System.out.println("人类定义错误！");
        } catch (ClassNotFoundException e) {
            System.out.println("混蛋，你指定的人类找不到！");
        }
        return human;
    }

    //女娲生气了，把一团泥巴塞到八卦炉，产生啥人类就啥人类
    public static Human createHuman(){
        Human human =null;//定义一个类型的人类

        //首先是获得多少个实现类，多少个类
        List<Class> concreateHumanList =
                ClassUtils.getAllClassByInterface(Human.class);//定义了多少人类
        Random random =new Random();
        int rand =random.nextInt(concreateHumanList.size());
        human =createHuman(concreateHumanList.get(rand));
        //八卦炉自己开始想少出人就什么人

        return human;
    }
}
