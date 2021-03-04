package design_pattern.decorator.scene01.main;

import design_pattern.decorator.scene01.abstraction.SchoolReport;
import design_pattern.decorator.scene01.abstraction.impl.FourGradeSchoolReport;

public class Father {//老爸看成绩单

	public static void main(String[] args) {
		SchoolReport sr =new FourGradeSchoolReport();

		sr.report();

	}

}

