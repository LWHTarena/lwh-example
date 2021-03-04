package design_pattern.state.scene02.main;

import design_pattern.state.scene02.inter.State;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>房间对象</p>
 * @version:v1.0
 */
public class HomeContext {

    //如果是银行系统，这个Context类就是账号。根据金额不同，切换不同的状态！
    private State state;

    public void setState(State state){
        System.out.println("修改状态！");
        state =state;
        state.handler();
    }
}
