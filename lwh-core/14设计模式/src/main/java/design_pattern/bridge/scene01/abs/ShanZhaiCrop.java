package design_pattern.bridge.scene01.abs;
/**
 * 桥梁模式【Bridge Pattern】
 * 我是山寨老大，你流行啥我就生产啥
 * @author liwenhao
 *
 * 现在流行Ipod
 *
 */
public class ShanZhaiCrop extends Crop {
	//产什么产品，不知道，等被调用的才知道
	public ShanZhaiCrop(Product product){
		super(product);

	}

	public void makeMoney(){
		super.makeMoney();
		System.out.println("ShanZhaiCrop.ShanZhaiCrop()...我赚钱啦");
	}

}
