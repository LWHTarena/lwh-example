package design_pattern.flyweight.scene02.impl;

import design_pattern.flyweight.scene02.ChessFlyWeight;
import design_pattern.flyweight.scene02.Coordinate;

/**
 * Created by Administrator on 2017/2/26.
 */
public class ConcreteChess implements ChessFlyWeight {
    private String color;

    public ConcreteChess(String color) {
        super();
        this.color = color;
    }

    @Override
    public void display(Coordinate c) {
        System.out.println("棋子颜色："+color);
        System.out.println("棋子位置："+c.getX()+"----"+c.getY());
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String c) {
        this.color = c;
    }
}
