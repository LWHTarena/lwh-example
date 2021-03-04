package design_pattern.composite.scene01.advance优化;

/**leaf是树叶节点，在这里就是我们这些小兵
 * 普通员工很简单，就写一个构造函数就是可以了*/
public class Leaf extends Corp {

	public Leaf(String name, String position, int salary) {
		super(name, position, salary);
	}

}
