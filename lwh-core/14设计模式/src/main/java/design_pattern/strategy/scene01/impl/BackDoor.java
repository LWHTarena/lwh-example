package design_pattern.strategy.scene01.impl;


import design_pattern.strategy.scene01.IStrategy;

/**
 * @author：liwenhao
 * @Date:2017/2/18
 * @description:<p> 找乔国老帮忙，是孙权不能杀刘备</p>
 * @version:v1.0
 */
public class BackDoor implements IStrategy {
    @Override
    public void operate() {
        System.out.println("找乔国老帮忙，让吴国太给孙权施加压力");
    }
}
