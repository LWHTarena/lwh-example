package design_pattern.builder.scene01.main;

import design_pattern.builder.scene01.model.BenzModel;

import java.util.ArrayList;


public class Client {

	public static void main(String[] args) {

		BenzModel benz =new BenzModel();
		ArrayList<String> sequence =new ArrayList<String>();//存放run的顺序

		sequence.add("engine boom");//客户要求，run的时候先发动引擎
		sequence.add("start");
		sequence.add("stop");

		//然后我们把这个顺序给奔驰：
		benz.setSequence(sequence);
		benz.run();

	}

}
