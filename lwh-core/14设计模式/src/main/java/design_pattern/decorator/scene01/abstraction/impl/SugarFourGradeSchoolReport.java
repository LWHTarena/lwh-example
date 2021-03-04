package design_pattern.decorator.scene01.abstraction.impl;
/**
 * 四年级的成绩单，这个就是学校第一次实施
 */
public class SugarFourGradeSchoolReport extends FourGradeSchoolReport {

	//首先先定义要美化的方法，先给老爸说学校最高成绩
	private void reportHighShool(){
		System.out.println("这次的考试语文98，数学100，自然99");
	}

	//再老爸看完毕业成绩单后，我在汇报学校的排名情况
	private void reportSort(){
		System.out.println("我是排名第一...");
	}

	//由于汇报的内容发生变更，那所以要重写父类
	public void report(){
		this.reportHighShool();
		super.report();
		this.reportSort();

	}
}