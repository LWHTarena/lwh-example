package design_pattern.mediator.scene01.advance2;

/**中介者模式有以下几部分:
 * 抽象中介者（Mediator）角色：抽象中介者角色定义统一的接口用于各种同事角色之间的通信
 * 具体中介者（Concrete Mediator）模式：具体中介者角色通过协调各种同事角色实现协作行为，因此它依赖于各个同事角色
 * 同事（Colleague）角色:
 *
 * 具体的中介者一般只有一个，其源码结构
 * 中介者所具有的方法都是比较复杂的业务逻辑，都是为同事类服务的。
 * @author liwenhao
 *
 */
public class ConcreteMediator extends Mediator {

	@Override
	public void doSomething1() {
		//调用同事类的方法，只要是public方法都可以调用
		super.c1.selfMethod1();
		super.c2.selfMethod2();
	}

	@Override
	public void doSomething2() {
		super.c1.selfMethod1();
		super.c2.selfMethod2();
	}

}

