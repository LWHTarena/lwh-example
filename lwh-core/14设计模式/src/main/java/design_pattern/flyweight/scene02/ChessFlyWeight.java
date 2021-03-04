package design_pattern.flyweight.scene02;

/**
 * Created by Administrator on 2017/2/26.
 * <p>享元类</p>
 */
public interface ChessFlyWeight {
    void setColor(String c);
    String getColor();
    void display(Coordinate c);
}
