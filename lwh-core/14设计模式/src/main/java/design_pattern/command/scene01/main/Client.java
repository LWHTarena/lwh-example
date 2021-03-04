package design_pattern.command.scene01.main;

import design_pattern.command.scene01.abstraction.Group;
import design_pattern.command.scene01.abstraction.PageGroup;

/**
 * 客户就是甲方,给我们前的一方,是老大
 */
public class Client {
	public static void main(String[] args){
		//首先客户找到美工组说,过来谈页面,并修改
		System.out.println("Client.main()------客户要求删除一个页面-----");
		Group pg =new PageGroup();
		//找到需求组
		pg.find();

		//增加一个需求
		pg.delete();

		//要求变更计划
		pg.plan();

	}


}
