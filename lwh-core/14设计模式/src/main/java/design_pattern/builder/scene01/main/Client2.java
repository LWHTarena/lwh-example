package design_pattern.builder.scene01.main;

import design_pattern.builder.scene01.model.BenzBuilder;
import design_pattern.builder.scene01.model.BenzModel;

import java.util.ArrayList;


public class Client2 {

	public static void main(String[] args) {

		ArrayList<String> sequence =new ArrayList<String>();//存放run的顺序

		sequence.add("engine boom");//客户要求，run的时候先发动引擎
		sequence.add("start");
		sequence.add("stop");

		//要一个奔驰
		BenzBuilder benzBuilder =new BenzBuilder();
		benzBuilder.setSequence(sequence);
		BenzModel benz =(BenzModel) benzBuilder.getCarModel();
		benz.run();

	}

}
