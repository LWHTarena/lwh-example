package design_pattern.adapter.scene02.inter;

/**
 * 对象适配器
 *
 * 这个对象适配器关键持有了以前的老功能，老版本的引用
 */
public class ObjectAdapter implements NewInterface2 {

    NewInterface ni;

    public ObjectAdapter(NewInterface ni) {
        this.ni =ni;
    }

    @Override
    public void sleep() {
        ni.eat();
        System.out.println("我是新版本2的需求，对老功能进行了扩展，增加睡觉功能需求");
    }
}
