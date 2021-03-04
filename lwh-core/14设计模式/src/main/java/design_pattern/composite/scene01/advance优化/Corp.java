package design_pattern.composite.scene01.advance优化;
/**
 * 抽象构件角色（Component）：定义参加组合的对象的共有方法和属性，可以定义一些默认的行为或属性：
 *        比如我们例子中的getInfo（）就封装到了抽象类中
 * 叶子构件（Leaf）：叶子对象，其下再没有其他分支
 * 树枝构件（Composite）：树枝对象，它的作用是组合树枝节点和叶子节点
 *
 * 组合模式有两种模式：透明模式和安全模式
 * 透明模式就是把用来组合使用的方法放到抽象类中
 *
 * 组合模式的优点：只要是树形结构，考虑用组合模式（一定要记住，只要是体现局部和整体的关系的时候，还而且
 * 这种关系还可能比较深，考虑一下组合模式）
 * 组合模式有一个明显的缺点  直接使用了实现类
 *
 *定义一个公司的人员的抽象类
 */
public abstract class Corp {

	private String name ="";

	private String position ="";

	private int salary =0;

	/*通过接口的方式传递，我们改变一下习惯，传递进来的参数名以下
	 * 划线开始
	 * 这个在一些开源项目中非常常见，一般构造函数都是这么定义的
	 */
	public Corp(String name,String position,int salary){
		this.name =name;
		this.position =position;
		this.salary =salary;
	}

	//获得员工信息
	public String getInfo(){
		String info ="";
		info ="名称："+this.name;
		info =info+"\t职位："+this.position;
		info =info+"\t薪水："+this.salary;
		return info;
	}

}
