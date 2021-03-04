package design_pattern.composite.scene01.advance;

import java.util.ArrayList;
/**这些树节点也就是这些领导们既要有自己的信息，还要知道自己下属的情况*/
public class Branch implements IBranch {

	private String name ="";

	private String position ="";

	private int salary =0;

	public Branch(String name,String position,int salary){
		this.name =name;
		this.position =position;
		this.salary =salary;
	}

	//领导下边还有那些下级领导和小兵
	ArrayList<ICorp> subordinateList =
			new ArrayList<ICorp>();

	//增加一个下属可能是小头目，也可能是个小兵
	@Override
	public void addSubordinate(ICorp crop) {
		this.subordinateList.add(crop);
	}

	//我有哪些下属
	@Override
	public ArrayList<ICorp> getSubordinate() {
		return this.subordinateList;
	}

	//领导也是人，她也有信息
	public String getInfo(){
		String info ="";
		info ="名称："+this.name;
		info =info+"\t职位："+this.position;
		info =info+"\t薪水："+this.salary;
		return info;
	}

}
