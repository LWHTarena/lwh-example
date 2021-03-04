package design_pattern.composite.scene01.advance优化;

import java.util.ArrayList;
/**这些树节点也就是这些领导们既要有自己的信息，还要知道自己下属的情况
 * 节点类，也简单了很多*/
public class Branch extends Corp {

	//领导下边有哪些下级领导和小兵
	ArrayList<Corp> subordinateList =new ArrayList<Corp>();

	public Branch(String name, String position, int salary) {
		super(name, position, salary);
	}

	//增加一个下属可能是小头目，也可能是个小兵
	public void addSubordinate(Corp crop) {
		this.subordinateList.add(crop);
	}

	//我有哪些下属
	public ArrayList<Corp> getSubordinate() {
		return this.subordinateList;
	}

}
