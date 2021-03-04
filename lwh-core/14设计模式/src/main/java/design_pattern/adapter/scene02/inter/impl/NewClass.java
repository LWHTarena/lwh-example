package design_pattern.adapter.scene02.inter.impl;

import design_pattern.adapter.scene02.Source;
import design_pattern.adapter.scene02.inter.NewInterface;

/**
 * 继承了Source，就意味着继承了V1版本功能，然后实现了NewInterface接口，就意味着可以对以前的V1版本进行了扩展
 *
 * 类的适配器（创建一个新类继承了老类，实现了的新接口，这种方式称为类的适配器）
 */
public class NewClass extends Source implements NewInterface{
    @Override
    public void sleep() {
        System.out.println("我是新版本的需求，对老功能进行了扩展，增加睡觉功能需求");
    }
}
