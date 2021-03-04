package design_pattern.bridge.scene01.main;

import design_pattern.bridge.scene01.abs.HouseCrop;
import design_pattern.bridge.scene01.abs.ShanZhaiCrop;
import design_pattern.bridge.scene01.abs.impl.House;
import design_pattern.bridge.scene01.abs.impl.IPod;

/**
 * 把服装公司改为山寨公司，及时生产流行的产品
 * @author liwenhao
 * 记住：业务抽象角色引用业务实现角色，或者说业务抽
 * 象角色的部分实现是由业务实现角色完成的
 *
 * 桥梁模式的优点就是类间解耦--符合OCP原则
 *
 */
public class Client2 {

	public static void main(String[] args) {
		House house =new House();
		System.out.println("Client.main()------房地产公司就是这个样子运行的------");

		//先找到我的公司
		HouseCrop houseCrop =new HouseCrop(house);
		//看我怎样挣钱
		houseCrop.makeMoney();
		System.out.println("\n");

		System.out.println("Client.main()------山寨公司是这样运行的------");
		ShanZhaiCrop shanZhaiCrop =new ShanZhaiCrop(new IPod());
		shanZhaiCrop.makeMoney();
	}

}
