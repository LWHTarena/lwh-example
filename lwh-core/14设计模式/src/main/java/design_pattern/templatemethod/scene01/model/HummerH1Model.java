package design_pattern.templatemethod.scene01.model;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 悍马是每个越野者的最爱，其中H1最接近军用系列
 *
 * H1有所不同，它的喇叭要不要响是有客户来决定
 * </p>
 * @version:v1.0
 */
public class HummerH1Model extends HummerModel {
    private boolean alarmFlag =true;//是否要响喇叭

    @Override
    public void alarm() {
        System.out.println("HummerH1Model.alarm()...悍马H1鸣笛");

    }

    @Override
    public void engineBoom() {
        System.out.println("HummerH1Model.engineBoom()...悍马H1引擎声音是这样");

    }

    @Override
    public void start() {
        System.out.println("HummerH1Model.start()...悍马H1发动");

    }

    @Override
    public void stop() {
        System.out.println("HummerH1Model.stop()...悍马H1停车");

    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    //要不要响喇叭，是由客户的来决定的
    public void setAlarm(boolean isAlarm){
        this.alarmFlag =isAlarm;
    }


    /**
     * 这段代码修改了两个地方，一是重写了父类的isAlarm()方法，
     * 一是增加了一个setAlarm()方法，由调用者决定是否要这个功能
     */

}
