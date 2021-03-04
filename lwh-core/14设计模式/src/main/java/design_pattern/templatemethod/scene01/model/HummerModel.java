package design_pattern.templatemethod.scene01.model;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 模板方法模式【Template Mothod Pattern】
 * HummerModel 是悍马车辆模型的意思
 * </p>
 * @version:v1.0
 */
public abstract class HummerModel {
    /**
     * 首先，这个模型要能够被发动起来，别管是手摇发动，还是电力
     * 发动，反正是要能够发动起来，那就实现要在实现类里了
     */

    protected abstract void start();

    //能发动，那还要能停下来，那才是真本事
    protected abstract void stop();

    //喇叭会出声音，是滴滴叫，还是哔哔叫
    protected abstract void alarm();

    //引擎会轰隆隆的响，不响那是假的
    protected abstract void engineBoom();

    //那模型应该会跑吧，别管是人推的，还是电力驱动，总之要会跑
    public final void run(){
        //先发动汽车
        this.start();

        //引擎开始轰鸣
        this.engineBoom();

        //喇叭想让它响就响，不想让它响就不响
        if(this.isAlarm()){
            this.alarm();
        }

        //到达目的地就停车
        this.stop();

    }

    //钩子方法，默认喇叭是会响的
    protected boolean isAlarm(){
        return true;
    }

    /**
     * 其他的子类都不用修改（如果要修改，就是把四个方法
     * 的访问权限由public修改为protected）。请看run（）方法
     * ，它定义了调用其他方法的顺序，并且子类是不能修改的，这
     * 个模板方法：start、stop、alarm、engineBoom这四个方法是
     * 子类实现的，而且这四个方法的修改对应了不同的类，这个叫做
     * 基本方法。         我们这四个基本方法就是抽象方法，有子类来
     * 实现的。
     *
     * 基本方法有分三种：
     * 		在抽象类中实现了的基本方法叫做具体方法；
     * 		在抽象中没有实现，在子类中实现了叫做抽象方法；
     * 		还有钩子方法--钩子方法模式是由抽象类来实现的，
     * 子类可以重写的，H2型号的悍马是不会叫的，喇叭是个摆
     * 设
     *
     * 	老板说，车子一启动，喇叭就狂响，设计缺陷。
     *  增加一个方法，isAlarm（）,喇嘛要不要响，这就是钩子方法（Hook Method）
     */
}
