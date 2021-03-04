package design_pattern.decorator.scene01.main;

import design_pattern.decorator.scene01.abstraction.SchoolReport;
import design_pattern.decorator.scene01.abstraction.impl.FourGradeSchoolReport;
import design_pattern.decorator.scene01.abstraction.impl.HighSchoolReport;
import design_pattern.decorator.scene01.abstraction.impl.SortDecorator;

public class Father3 {//老爸看成绩单

	public static void main(String[] args) {
		//成绩单拿过来
		SchoolReport sr ;

		sr =new FourGradeSchoolReport();//原装的成绩单（未经任何装饰）

		//加了 最高分说明的成绩单
		sr =new HighSchoolReport(sr);

		//又加了成绩单排名的说明
		sr =new SortDecorator(sr);

		//看成绩单
		sr.report();

		//然后老爸一看，很开心，就签名了
		sr.sign("李总");
	}

}

