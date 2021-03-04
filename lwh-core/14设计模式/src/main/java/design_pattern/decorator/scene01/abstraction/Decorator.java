package design_pattern.decorator.scene01.abstraction;
/**
 * 装饰模式【Decorator Pattern】
 * 一旦需要的装饰条件非常多，比如20个，你还通过继承来解决吗？
 * 你想想的子类有多少个？你是不是马上就要奔溃了
 * 		通过继承情况确实出现问题了，类爆炸，类的数量激增，光
 * 写这些类不累死你才怪，而且还要想想以后怎么维护怎么办，谁愿
 * 意接受这么一大堆的维护呀？并且在面向对象的设计，如果超过2层
 * 继承，你就应该想想是不是设计出问题了，是不是应该重新找一条道？
 *
 * 继承层次越多你以后的维护成本就越多，怎么办呢？---装饰模式就很好的解决这些问题
 *
 */
public abstract class Decorator extends SchoolReport {
	//装饰类，我要把我的成绩单装饰一下
	//首先我要知道是哪个成绩单
	private SchoolReport sr;
	//构造函数，传递成绩单过来
	public Decorator(SchoolReport sr){
		this.sr =sr;
	}

	@Override
	public void report() {
		this.sr.report();

	}

	@Override
	public void sign(String name) {
		this.sr.sign(name);

	}

	/**
	 * Decorator抽象类的目的很简单，就是养子类来对封装SchoolReport的子类
	 * 装饰模式可以代替继承，解决我们类膨胀的问题.优点：扩展性非常好。
	 *     在一个项目中，你会有非常多因素考虑不到，特别是业务的变更，时不时
	 *     的冒出一个需求，特别提出一个令项目大量延迟的需求时候。。。通过
	 *     装饰模式重新封装一个类，而不是通过继承来完成
	 *
	 * 记住：再装饰模式中，必然有一个被提取出来最核心、最原始、最基本的接口或抽象类
	 *
	 * 在项目中要考虑易维护、易扩展、易复用等。
	 */

}
