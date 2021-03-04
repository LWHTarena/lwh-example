package design_pattern.decorator.scene01.abstraction.impl;

import design_pattern.decorator.scene01.abstraction.Decorator;
import design_pattern.decorator.scene01.abstraction.SchoolReport;

public class SortDecorator extends Decorator {

	public SortDecorator(SchoolReport sr) {
		super(sr);
	}

	//告诉老爸学校的排名
	public void reportSort(){
		System.out.println("我是排名第一...");
	}

	//老爸看完成绩单后再告诉她，加强作用
	public void report(){
		super.report();
		this.reportSort();

	}

}