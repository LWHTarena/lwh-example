package design_pattern.bridge.scene01.abs;


import design_pattern.bridge.scene01.abs.impl.Clothes;

/*
 * 服装公司
 */
public class ClothesCrop extends Crop {

	public ClothesCrop(Clothes clothes){
		super(clothes);
	}
	public void makeMoney(){
		super.makeMoney();
		System.out.println("ClothesCrop.makeMoney()...服装公司赚小钱");
	}

}
