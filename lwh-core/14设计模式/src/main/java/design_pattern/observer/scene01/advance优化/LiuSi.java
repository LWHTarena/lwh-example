package design_pattern.observer.scene01.advance优化;

import java.util.Observable;
import java.util.Observer;

public class LiuSi implements Observer {

    @Override
    public void update(Observable observable, Object obj) {
        System.out.println("刘斯：观察到韩非子活动，开始想老板汇报...");
        this.reportToQinShiHuang(obj.toString());
        System.out.println("刘斯：真被乐死了\n");

    }

    private void reportToQinShiHuang(String context) {
        System.out.println("刘斯：因为" + context + "--所以我快乐");
    }

}

