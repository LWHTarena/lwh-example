package design_pattern.flyweight.scene02;

import design_pattern.flyweight.scene02.impl.ConcreteChess;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 * <p>享元工厂类</p>
 */
public class ChessFlyWeightFactory {
    //享元池
    private static Map<String,ChessFlyWeight> map = new HashMap<String, ChessFlyWeight>();

    public static ChessFlyWeight  getChess(String color){

        if(map.get(color)!=null){
            return map.get(color);
        }else{
            ChessFlyWeight cfw = new ConcreteChess(color);
            map.put(color, cfw);
            return cfw;
        }

    }
}
