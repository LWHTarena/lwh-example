package design_pattern.visitor.scene01.common2;

import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String[] args) {

		for(Employee emp:mockEmployee()){
			emp.accept(new Visitor());
		}
	}

	private static List<Employee> mockEmployee() {
		List<Employee> empList =new ArrayList<Employee>();

		//产生张三这个员工
		CommonEmployee zhangSan =new CommonEmployee();
		zhangSan.setJob("编写java程序，绝对的蓝领，苦工加搬运工");
		zhangSan.setName("张三");
		zhangSan.setSalary(2800);
		zhangSan.setSex(Employee.MALE);
		empList.add(zhangSan);

		//产生李四这个员工
		CommonEmployee Lisi =new CommonEmployee();
		Lisi.setJob("页面美工，审计素质太不流行了！");
		Lisi.setName("李四");
		Lisi.setSalary(2900);
		Lisi.setSex(Employee.FEMAIL);
		empList.add(Lisi);

		//在产生一个经理
		Manager wangWu =new Manager();
		wangWu.setName("王五");
		wangWu.setPerformance("基本上是负值，但是会拍马屁呀！");
		wangWu.setSalary(18590);
		wangWu.setSex(Employee.MALE);
		empList.add(wangWu);

		return empList;
	}
}
