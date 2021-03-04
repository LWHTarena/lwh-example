package design_pattern.bridge.scene01.abs;

import design_pattern.bridge.scene01.abs.impl.IPod;

/**
 * 桥梁模式【Bridge Pattern】
 * 我是山寨老大，你流行啥我就生产啥
 * @author liwenhao
 *
 * 现在流行Ipod
 *
 */
public class IPodCrop extends Crop {

	public IPodCrop(IPod iPod){
		super(iPod);
	}
	public void makeMoney(){
		super.makeMoney();
		System.out.println("IPodCrop.makeMoney()...我赚钱啦");
	}

}
