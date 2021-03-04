package design_pattern.bridge.scene01.abs;
/*
 * 定义一个公司的抽象类
 */
public abstract class Crop {

	//定义一个产品对象，抽象的，不知道具体是什么产品
	private Product product;
	//构造函数，由子类定义传递具体的产品进来
	public Crop(Product product){
		this.product =product;
	}

	public void makeMoney(){
		//每个公司都是一样，先生产
		this.product.beProducted();

		//然后销售
		this.product.beSelled();

	}
}