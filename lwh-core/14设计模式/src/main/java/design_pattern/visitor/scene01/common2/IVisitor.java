package design_pattern.visitor.scene01.common2;
/**访问者模式【Visitor Pattern】
 * 访问者，我要去访问人家的数据了**/
public interface IVisitor {

	//首先定义我可以访问普通员工
	public void visit(CommonEmployee commonEmployee);

	//其次定义，我还可以访问部门经理
	public void visit(Manager manager);



	/**怎么实现的？
	 * 首先通过遍历所有元素
	 *
	 * 其次，每个员工对象都定义了一个访问者
	 *
	 * 再其次，员工对象把自己作为一个参数调用访问者visit方法
	 *
	 * 然后，访问者调用自己内部的计算逻辑，计算出相应的数据和表格元素
	 *
	 * 最后，访问者打印出报表和数据
	 *
	 */
}
