package design_pattern.visitor.scene01.extend2;
/*负责统计表的产生*/
public interface ITotalVisitor extends IVisitor {

	//统计所有员工工资总和
	public void totalSalary();
}