package design_pattern.visitor.scene01.common2;
/**
 * 抽象访问职位（Visitor）：抽象类或接口。声明访问者可以访问哪些元素，具体到程序中就是visit方法的参数定义哪些对象可以被访问的;
 * 具体访问者（ConcreteVisitor）：访问者访问到一个类后该怎么干；
 * 抽象元素（Element）：接口或者抽象类，声明接受哪一类型的访问者，程序上是通过acceot方法中的参数来定义
 * 具体元素（CncreteElement）：实现accpte方法，通常是visitor.visit（this），基本上是套路了
 * 结构对象（ObjectStruture：）实现多个不同类、不同接口的容器，比如List、Set、Map等，在项目中，一般很少抽象出来这个角色
 *
 * 访问者模式优点：符合单一原则。。。
 * 缺点：访问者要访问一个类就必须要求这个类公布一些方法，也就是访问者关注了其他类内部细节，这是迪米特法则所不建议的；
 * 还有具体劫色的增加删除修改部分比较苦难的
 *
 * 但是他结合其他模板方法模式（模板方法模式、状态模式、解析器模式、代理模式）就会很强大
 * @author liwenhao
 *
 */
public class Visitor implements IVisitor {

	//访问普通员工，打印出报表
	@Override
	public void visit(CommonEmployee commonEmployee) {
		System.out.println(this.getCommonEmployee(commonEmployee));

	}

	//访问部门经理，打印出报表
	@Override
	public void visit(Manager manager) {
		System.out.println(this.getManagerInfo(manager));
	}

	//组装出基信息
	private String getBasicInfo(Employee employee){
		String info ="姓名："+employee.getName()+"\t";
		info =info +"性别："+(employee.getSex() ==employee.FEMAIL?"女":"男")+"\t";
		info =info +"薪水："+employee.getSalary() +"\t";
		return info;
	}

	//组装出部门经理基本信息
	private String getManagerInfo(Manager manager){
		String basicInfo =this.getBasicInfo(manager);
		String otherInfo ="业绩:"+manager.getPerformance()+"\t";

		return (basicInfo+otherInfo);
	}

	//组装出普通员工信息
	private String getCommonEmployee(CommonEmployee commonEmployee) {
		String basicInfo =this.getBasicInfo(commonEmployee);
		String otherInfo ="工作："+commonEmployee.getJob()+"\t";

		return basicInfo+otherInfo;
	}


	/**
	 *
	 */

}
