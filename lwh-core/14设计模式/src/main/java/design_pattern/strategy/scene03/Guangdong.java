package design_pattern.strategy.scene03;

/**
 * 广东
 */
public class Guangdong implements Provice{

    private static String type ="guangdong";

    @Override
    public boolean isMyJob(String type) {
        return Guangdong.type.equals(type);
    }

    @Override
    public void doSomeThing(String type) {
        System.out.println("这个任务是我的，我是广东省:"+type);

    }
}
