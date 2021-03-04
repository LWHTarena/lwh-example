package design_pattern.mediator.scene01.advance;
/**
 * 中介者模式【Mediator Pattern】
 * @author liwenhao
 *
 */
public abstract class AbstactMediator {

	protected Purchase purchase;
	protected Sale sale;
	protected Stock stock;

	//构造函数
	public AbstactMediator(){
		purchase =new Purchase(this);
		sale =new Sale(this);
		stock =new Stock(this);
	}

	//中介者最重要的方法，叫做事件方法，处理多个对象之间的关系
	public abstract void execute(String str,Object...objects);
}