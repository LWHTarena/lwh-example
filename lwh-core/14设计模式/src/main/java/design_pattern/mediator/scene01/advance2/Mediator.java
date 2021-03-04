package design_pattern.mediator.scene01.advance2;

/**中介者模式是一个非常好的封装模式，也是一个很容易被滥用的模式，一个对象依赖几个对象是再正
 常不过的事情，但是纯理论家就会要求使用中介者模式来封装这种依赖关系，这是非常危险的信号，使用
 中介模式就必然会带来中介者的膨胀问题，这在一个项目中时很不恰当的，那到底在什么情况下使用中介
 者模式呢？大家可以在如下的情况下尝试使用中介者模式：
 1、 N个对象之间产生了相互的依赖关系，其中 N 大于 2，注意是相互的依赖；
 2、 多个对象有依赖关系，但是依赖的行为尚不确定或者有发生改变的可能，在这种情况下一般建议采
 用中介者模式，降低变更引起的风险扩散；
 3、 产品开发。其中一个明显的例子就是 MVC 框架，把这个应用到产品中，可以提升产品的性能和扩展
 性，但是作为项目开发就未必，项目是以交付投产为目标，而产品以稳定、高效、扩展为宗旨。

 * 中介者模式通用源码结构：
 */
public abstract class Mediator {

	//定义同事类
	protected ConcreteColleague1 c1;
	protected ConcreteColleague2 c2;

	//通过getter/setter方法把同事类注入进来
	public ConcreteColleague1 getC1(){
		return c1;
	}
	public void setC1(ConcreteColleague1 c1){
		this.c1 =c1;
	}
	public ConcreteColleague2 getC2(){
		return c2;
	}
	public void setC2(ConcreteColleague2 c2){
		this.c2 =c2;
	}


	//中介者模式的业务处理
	public abstract void doSomething1();
	public abstract void doSomething2();
}
