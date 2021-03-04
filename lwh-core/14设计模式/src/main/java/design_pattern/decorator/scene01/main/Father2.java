package design_pattern.decorator.scene01.main;


import design_pattern.decorator.scene01.abstraction.SchoolReport;
import design_pattern.decorator.scene01.abstraction.impl.SugarFourGradeSchoolReport;

public class Father2 {//老爸看成绩单

	public static void main(String[] args) {
		//美化过的成绩单拿过来
		SchoolReport sr =
				new SugarFourGradeSchoolReport();
		sr.report();

		//然后老爸一看，很开心，就签名了
		sr.sign("李总");
	}

}
