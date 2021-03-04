package design_pattern.mediator.scene01.advance2;

public class ConcreteColleague2 extends Colleague{
	//通过构造函数传递中介者
	public ConcreteColleague2(Mediator _mediator) {
		super(_mediator);
	}

	public void selfMethod2() {
		// 处理自己的业务逻辑

	}
	public void depMethod2(){
		// 处理自己的业务逻辑
		//自己不能处理的业务逻辑，委托中介者处理
		super.mediator.doSomething2();
	}
}
