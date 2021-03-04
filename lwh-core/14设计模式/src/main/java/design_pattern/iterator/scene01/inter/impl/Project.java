package design_pattern.iterator.scene01.inter.impl;


import design_pattern.iterator.scene01.inter.IProject;

public class Project implements IProject {
	/*所有项目的信息类*/

	//项目名称
	private String name ="";

	//项目成员数量
	private int num =0;

	//项目的费用
	private int cost =0;

	//定义一个构造函数，把所有老板需要看到信息存储起来
	public Project(String name,int num,int cost){
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

}

