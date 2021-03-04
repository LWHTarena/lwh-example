package design_pattern.strategy.scene03;

/**
 * 湖北
 */
public class Hubei implements Provice{

    private static String type ="hubei";

    @Override
    public boolean isMyJob(String type) {
        return Hubei.type.equals(type);
    }

    @Override
    public void doSomeThing(String type) {
        System.out.println("这个任务是我的，我是湖北省:"+type);

    }
}
