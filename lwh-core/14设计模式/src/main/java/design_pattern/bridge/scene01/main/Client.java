package design_pattern.bridge.scene01.main;


import design_pattern.bridge.scene01.abs.HouseCrop;
import design_pattern.bridge.scene01.abs.ShanZhaiCrop;
import design_pattern.bridge.scene01.abs.impl.Clothes;
import design_pattern.bridge.scene01.abs.impl.House;

public class Client {

	public static void main(String[] args) {
		House house =new House();
		System.out.println("Client.main()------房地产公司就是这个样子运行的------");

		//先找到我的公司
		HouseCrop houseCrop =new HouseCrop(house);
		//看我怎样挣钱
		houseCrop.makeMoney();
		System.out.println("\n");

		System.out.println("Client.main()------山寨公司是这样运行的------");
		ShanZhaiCrop shanZhaiCrop =new ShanZhaiCrop(new Clothes());
		shanZhaiCrop.makeMoney();
	}

}
