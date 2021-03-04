package design_pattern.strategy.scene03;

/**
 * 湖南
 */
public class Hunan implements Provice{

    private static String type ="hunan";

    @Override
    public boolean isMyJob(String type) {
        return Hunan.type.equals(type);
    }

    @Override
    public void doSomeThing(String type) {
        System.out.println("这个任务是我的，我是湖南省:"+type);

    }
}
