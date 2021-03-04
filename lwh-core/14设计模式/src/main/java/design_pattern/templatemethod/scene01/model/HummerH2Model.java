package design_pattern.templatemethod.scene01.model;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 悍马是每个越野者的最爱，其中H1最接近军用系列
 * H1和H2有什么差别，还真不知道，真没接触过悍马
 * </p>
 * @version:v1.0
 */
public class HummerH2Model extends HummerModel{
    @Override
    public void alarm() {
        System.out.println("HummerH2Model.alarm()...悍马H2鸣笛");

    }

    @Override
    public void engineBoom() {
        System.out.println("HummerH2Model.engineBoom()...悍马H2引擎声音是这样");

    }

    @Override
    public void start() {
        System.out.println("HummerH2Model.start()...悍马H2发动");

    }

    @Override
    public void stop() {
        System.out.println("HummerH2Model.stop()...悍马H2停车");

    }

    @Override
    protected boolean isAlarm() {
        return false;
    }
}
