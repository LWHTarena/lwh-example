package design_pattern.adapter.scene02.inter;

/**
 * 实现接口，可以重写
 * 这个是接口适配器，说白了我只要关心我的方法就行了
 */
public class NewInterfClass extends NewObstractClass {

    @Override
    public void sleep() {
        System.out.println("这个是接口适配器，我只需要重写我关心的方法即可！！！");
    }
}
