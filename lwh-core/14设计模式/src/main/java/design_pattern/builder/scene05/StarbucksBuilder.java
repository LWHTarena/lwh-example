package design_pattern.builder.scene05;

/**
 * <p>
 * <h2>简述：</h2>
 * <ol></ol>
 * <h2>功能描述：抽象builder</h2>
 * <ol></ol>
 * </p>
 *
 * @Author: liwh
 * @Date :
 * @Version: 版本
 */
public abstract class StarbucksBuilder {
    protected Starbucks starbucks;

    public Starbucks getStarbucks() {
        return starbucks;
    }

    public void createStarbucks() {
        starbucks = new Starbucks();
        System.out.println("a drink is created");
    }

    public abstract void buildSize();
    public abstract void buildDrink();
}
