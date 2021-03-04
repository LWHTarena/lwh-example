package design_pattern.adapter.scene01.main;

import design_pattern.adapter.scene01.inter.IUserInfo;
import design_pattern.adapter.scene01.inter.impl.OuterUserInfo;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>
 * 这就是我们的具体应用了，比如
 * 老板要查所有的20-30的女性信息
 * </p>
 * @version:v1.0
 */
public class App2 {
    public static void main(String[] args) {
        //没有雨外系统连接的时候，是这样写的
//		IUserInfo youngGirl =new UserInfo();

        IUserInfo youngGirl =new OuterUserInfo();
        //从数据库中查到102个
        for(int i=0;i<101;i++){
            youngGirl.getMobileNumber();
        }
    }

    /**
     * 实际上只是增加了一个业务类的继承
     * 以上是类对象适配器,还有对象适配器
     * 适配器模式不适合在系统设计阶段采用
     */
}
