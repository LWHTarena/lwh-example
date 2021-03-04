package design_pattern.iterator.scene01.inter.impl;

import design_pattern.iterator.scene01.inter.IProject2;
import design_pattern.iterator.scene01.inter.IProjectIterator;

import java.util.ArrayList;

public class Project2 implements IProject2 {
	/*所有项目的信息类*/
	private ArrayList<IProject2> projectList =
			new ArrayList<IProject2>();//定义一个项目列表，所有的项目都放在这里

	//项目名称
	private String name ="";

	//项目成员数量
	private int num =0;

	//项目的费用
	private int cost =0;

	public Project2(){}

	//定义一个构造函数，把所有老板需要看到信息存储起来
	public Project2(String name,int num,int cost){
		//赋值带类的成员变量中
		this.name =name;
		this.num =num;
		this.cost =cost;
	}
	@Override
	public String getProjectInfo() {
		String info ="";

		//获得项目的名称
		info =info+"项目名称是："+this.name;

		//获得项目人数
		info =info+"\t项目人数："+this.num;

		//项目费用
		info =info+"\t项目费用："+this.cost;
		return info;
	}

	@Override
	public void add(String name, int num, int cost) {
		this.projectList.add(new Project2(name,num,cost));

	}

	@Override
	public IProjectIterator iterator() {
		return new ProjectIterator(this.projectList);
	}

}
