package design_pattern.visitor.scene01.extend2;
/*负责展示报表的产生*/
public interface IShowVisitor extends IVisitor {
	//展示报表
	public void report();

}

