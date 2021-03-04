package design_pattern.singleton.scene01.singleton;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 单例模式很简单，就是在构造函数中多加了一个构造函数，访问权限是private
 * 就可以了
 * 但是会有风险？
 * 		在一个B/S项目中，每一个HTTP Reques 请求到J2EE的容器上都
 * 创建了一个线程，每一个线程都要创建一个单例对象,怎么办呢？
 * 好，我们写一个通用的单例程序，然后分析一下：
 * </p>
 * @version:v1.0
 */
public class SingletonPattern {
    private static SingletonPattern singletonPattern =null;

    //限制住不能直接产生一个实例
    private SingletonPattern(){}

    public SingletonPattern getInstance(){
        if(this.singletonPattern ==null){//如果还没有实例，则创建一个
            this.singletonPattern =
                    new SingletonPattern();
        }
        return this.singletonPattern;
    }

    /**
     * 假如现在两个线程A和线程B，线程A执行到this.singletonPattern =new SingletonPattern();
     * 正在申请内存分配，可能需要0.001微秒，线程B执行到if(this.singletonPattern ==null)
     * 你说这个时候这个判断条件是true还是false？是true，那么然后呢？
     * 线程B也往下走，于是乎就在内存中就有两个SingletonPattern的实例了，看看是不是出问题了？
     * 如果你这个单例是去拿一个序列号或者创建一个信号资源的时候，会怎样？业务逻辑混乱！数据一致性
     * 校验失败！最重要的是你从代码上还看出什么问题，这才是最重要的！因为这种情况基本是哪个你是重现
     * 不了的，不寒而栗吧，那怎么修改呢？我就说一种，能简单的，彻底解决问题的方案
     */
}
