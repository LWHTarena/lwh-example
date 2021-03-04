package design_pattern.builder.scene04;


import org.junit.Test;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class TestClient {

    @Test
    public void test(){

        Director director =new Director();

        /**
         * 直接拿电脑
         */
        Computer computer =director.getComputer();

        /**
         * 组装人员展示电脑给小成看
         */
        computer.show();
    }
}
