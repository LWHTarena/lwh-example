package design_pattern.builder.scene01.model;

import java.util.ArrayList;

public class BMWBuilder extends CarBuilder {

	private BMWModel bmw =new BMWModel();

	@Override
	public CarModel getCarModel() {
		return this.bmw;
	}

	@Override
	public void setSequence(ArrayList<String> sequence) {
		this.bmw.setSequence(sequence);
	}


	/**
	 * 程序很简单，很实用
	 * 最欣赏这样的程序员
	 */
}

