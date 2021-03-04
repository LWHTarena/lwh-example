package design_pattern.flyweight.scene02;

/**
 * Created by Administrator on 2017/2/26.
 * <p>外部状态UnSharedConcreteFlyWeight</p>
 */
public class Coordinate {
    private int x,y;

    public Coordinate(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
