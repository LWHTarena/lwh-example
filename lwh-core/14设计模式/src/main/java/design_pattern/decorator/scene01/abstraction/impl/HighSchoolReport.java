package design_pattern.decorator.scene01.abstraction.impl;

import design_pattern.decorator.scene01.abstraction.Decorator;
import design_pattern.decorator.scene01.abstraction.SchoolReport;

public class HighSchoolReport extends Decorator {

	//构造函数
	public HighSchoolReport(SchoolReport sr) {
		super(sr);
	}

	//我要汇报最高成绩
	private void reportHighSchool(){
		System.out.println("这次的考试语文98，数学100，自然99");
	}

	public void report(){
		this.reportHighSchool();
		super.report();
	}
}
