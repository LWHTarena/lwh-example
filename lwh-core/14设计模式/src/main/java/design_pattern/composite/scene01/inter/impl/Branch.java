package design_pattern.composite.scene01.inter.impl;

import design_pattern.composite.scene01.inter.IBranch;
import design_pattern.composite.scene01.inter.ILeaf;

import java.util.ArrayList;

/*所有的树节点*/
public class Branch implements IBranch {

	//存储子节点的信息
	private ArrayList subordinateList =new ArrayList();

	//树枝节点的名称
	private String name ="";

	//树枝节点的职位
	private String position ="";

	//树枝节点的薪水
	private int salary =0;

	//通过构造函数传递树枝节点大参数
	public Branch(String name,String position,int salary){
		this.name =name;
		this.position =position;
		this.salary =salary;
	}

	@Override
	public void add(IBranch branch) {//增加一个子树枝节点
		this.subordinateList.add(branch);

	}

	@Override
	public void add(ILeaf leaf) {//增加一个叶子节点
		this.subordinateList.add(leaf);

	}

	@Override
	public String getInfo() {
		String info ="";
		info ="名称："+this.name;
		info =info+"\t职位："+this.position;
		info =info+"\t薪水："+this.salary;
		return info;
	}

	@Override
	public ArrayList getSubordinateInfo() {
		return this.subordinateList;
	}

}

