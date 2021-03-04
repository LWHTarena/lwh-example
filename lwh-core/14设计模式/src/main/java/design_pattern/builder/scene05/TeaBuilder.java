package design_pattern.builder.scene05;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：泡茶</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public class TeaBuilder extends StarbucksBuilder {

    public void buildSize() {
        starbucks.setSize("large");
        System.out.println("build large size");
    }

    public void buildDrink() {
        starbucks.setDrink("tea");
        System.out.println("build tea");
    }
}
