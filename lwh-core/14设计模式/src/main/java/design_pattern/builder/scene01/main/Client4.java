package design_pattern.builder.scene01.main;

import design_pattern.builder.scene01.util.Director;



public class Client4 {

	public static void main(String[] args) {
		Director director =new Director();

		//1我、辆A类型的奔驰
		for(int i=0;i<10000;i++){
			director.getABenzModel().run();
		}

		//100w辆B类型的奔驰
		for(int i=0;i<1000000;i++){
			director.getBBenzModel().run();
		}

		//1000w辆C类型的宝马
		for(int i=0;i<10000000;i++){
			director.getCBMWModel().run();
		}
	}

	/**
	 * CarModel以及两个实现类BnezModel和BMWModel叫做产品类（Product Class），
	 * 这个产品类实现了模板方法模式，也就是模板方法和基本方法
	 * CarBuilder以及两个实现类BenzBuilder和BMWBuilder叫做建造者（Builder Class）。
	 * Director类叫做导演类（Director Class），负责安排已有模块的顺序，然后
	 * 告诉Builder开始建造
	 * 这个建造者模式和工厂模式非常相似，但是记住一点就可以游刃有余的使用了：建造者
	 * 模式最主要功能就是基本方法的调用顺序安排，也就是这些基本方法已经实现了；而
	 * 工厂方法重点就是创建，你要什么对象我创建一个对象出来，组装顺序则不是她关心的。
	 *
	 * 		建造者模式使用的场景，一是产品类非常的复杂或者产品类中的调顺序不同产生了冉的效能，
	 * 这个时候使用建造者模式是非常合适。
	 *
	 */

}
