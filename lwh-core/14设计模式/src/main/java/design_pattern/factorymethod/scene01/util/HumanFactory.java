package design_pattern.factorymethod.scene01.util;

import design_pattern.factorymethod.scene01.info.Human;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p> * 工厂方法模式【Factory Method Pattern】</p>
 * @version:v1.0
 */
public class HumanFactory {
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
}
