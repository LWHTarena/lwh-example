package design_pattern.strategy.scene03;

import java.util.ArrayList;
import java.util.List;

public class MyTest {
    public static void main(String[] args) {

        String type ="guangdong"; //模拟前端传过来的数据

        Provice hunan =new Hunan();
        Provice hubei =new Hubei();
        Provice guangdong =new Guangdong();

        List<Provice> lists =new ArrayList<>();
        lists.add(guangdong);
        lists.add(hubei);
        lists.add(hunan);

        for(Provice p:lists){
            if(p.isMyJob(type)){
                p.doSomeThing(type);
            }
        }
    }
}
