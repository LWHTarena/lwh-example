package design_pattern.decorator.scene01.abstraction;
/**
 * 成绩单的抽象类
 * create时间：2014-1-1
 *
 */
public abstract class SchoolReport {

	//成绩单的主要展示的就是你的成绩情况
	public abstract void report();

	//成绩单要家长签字，这个是最要命的
	public abstract void sign(String name);

}
