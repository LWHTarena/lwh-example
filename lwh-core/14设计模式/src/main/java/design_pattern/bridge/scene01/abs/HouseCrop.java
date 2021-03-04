package design_pattern.bridge.scene01.abs;

import design_pattern.bridge.scene01.abs.impl.House;

/*
 * 房地产公司
 */
public class HouseCrop extends Crop {
	//定义传递一个House产品进来
	public HouseCrop(House house){
		super(house);
	}

	public void makeMoney(){
		super.makeMoney();
		System.out.println("HouseCrop.makeMoney()...房地产公司赚大钱了");
	}
}