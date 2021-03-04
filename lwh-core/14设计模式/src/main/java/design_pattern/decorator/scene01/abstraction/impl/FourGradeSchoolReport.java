package design_pattern.decorator.scene01.abstraction.impl;

import design_pattern.decorator.scene01.abstraction.SchoolReport;

/**
 * 四年级的成绩单，这个就是学校第一次实施
 */
public class FourGradeSchoolReport extends SchoolReport {

	@Override
	public void report() {
		//成绩单的格式是这个样子的
		System.out.println("尊敬的xxx家长");
		System.out.println("	......		");
		System.out.println("语文98，数学100，自然99");
		System.out.println("	......		");
		System.out.println("		家长签名：			");

	}

	public void sign(String name) {
		System.out.println("FourGradeSchoolReport.sign()...家长签名："+name);
	}

}
