package design_pattern.builder.scene02;

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

    public static void main(String[] args) {
        Director director =new Director();
        Product p =director.getAProduct();
        System.out.println(p.toString());
    }
}
