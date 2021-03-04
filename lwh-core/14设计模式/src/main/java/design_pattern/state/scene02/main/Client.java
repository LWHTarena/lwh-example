package design_pattern.state.scene02.main;

import design_pattern.state.scene02.inter.impl.BookedState;
import design_pattern.state.scene02.inter.impl.FreeState;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p>客户端测试</p>
 * @version:v1.0
 */
public class Client {
    public static void main(String[] args) {
        HomeContext ctx =new HomeContext();
        ctx.setState(new FreeState());
        ctx.setState(new BookedState());
    }
}
