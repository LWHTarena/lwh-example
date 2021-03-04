package design_pattern.strategy.scene01;

import design_pattern.strategy.scene01.impl.BackDoor;
import design_pattern.strategy.scene01.impl.BlockEnemy;
import design_pattern.strategy.scene01.impl.GiveGreenLinght;
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

    /**
     * <p>赵云揣着三个锦囊</p>
     * 赵云出场了，他根据诸葛亮给他的交代，依次拆开妙计
     */
    @Test
    public void zhaoyun(){
        Context context;

        /**
         * 刚刚到吴国的时候拆第一个
         */
        System.out.println("----------刚刚到吴国的时候拆第一个---------");
        context =new Context(new BackDoor());//拿到妙计
        context.operate();
        System.out.println("\n\n\n");

        //刘备乐不思蜀了，拆第二个了
        System.out.println("----------刘备乐不思蜀了，拆第二个了---------");
        context =new Context(new GiveGreenLinght());
        context.operate();
        System.out.println("\n\n\n");

        //孙权的小兵追了，咋办？拆第三个
        System.out.println("----------孙权的小兵追了，咋办？拆第三个--------");
        context =new Context(new BlockEnemy());
        context.operate();//孙夫人退兵
        System.out.println("\n\n\n");


        /**
         * 问题来了：赵云实际不知道是哪个策略呀，他只知道拆第一个锦囊，
         * 而不知道是BackDoor这个妙计，咋办？似乎这个策略模式已经把计谋名称写出来了
         *
         *
         * 策略模式的好处就是：体现了高内聚低耦合的特性呀
         *  策略模式利用组合、委托和多态等技术和思想，可以有效地避免多重条件选择语句。
         * 	使用策略模式可以避免使用多重条件(if-else)语句。多重条件语句不易维护，它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重条件语句里面，比使用继承的办法还要原始和落后。
         * 缺点：
         * 	客户端必须知道所有的策略类，并自行决定使用哪一个策略类。
         *  由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。
         *
         * 还有扩展性，也就是OCP原则，策略类可以据需增加下去，只要修改
         * Context.java就可以了
         *
         * 区别 if-else 和状态模式
         * 使用场景：
         */
    }
}
