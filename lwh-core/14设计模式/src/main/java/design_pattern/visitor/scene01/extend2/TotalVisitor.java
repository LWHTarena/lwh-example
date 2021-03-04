package design_pattern.visitor.scene01.extend2;
/**
 * 汇总表，该访问者起汇总作用，把容器中的数据
 * 一个一个遍历，然后汇总
 * @author liwenhao
 *
 */
public class TotalVisitor implements ITotalVisitor {

	//部门经理的工资系数是5
	private final static int MANAGER_COEFFICIENF =5;

	//员工的工资系数是2
	private final static int COMMONEMPLOYEE_COEFFICIENF =2;

	//普通员工的工资总和
	private int commonTotalSalary =0;

	//部门经理的工资总和
	private int managerTotalSalary =0;

	@Override//访问普通员工，计算工资总额
	public void visit(CommonEmployee commonEmployee) {
		this.commonTotalSalary =
				this.commonTotalSalary+commonEmployee.getSalary()*COMMONEMPLOYEE_COEFFICIENF;

	}

	@Override//访问部门经理，计算工资总额
	public void visit(Manager manager) {
		this.managerTotalSalary =
				this.managerTotalSalary+manager.getSalary()*MANAGER_COEFFICIENF;

	}

	@Override
	public void totalSalary() {
		System.out.println("本月的月工资总额是:"+(this.commonTotalSalary+this.managerTotalSalary));

	}

}
