package design_pattern.iterator.scene01.main;


import design_pattern.iterator.scene01.inter.IProject2;
import design_pattern.iterator.scene01.inter.IProjectIterator;
import design_pattern.iterator.scene01.inter.impl.Project2;

/**
 * 迭代器模式有点落后了，基本上很少项目在独立写迭代器了
 * 直接用List或者Map就可以完整的解决问题。
 *
 * 老板来看项目信息了
 */
public class Boss2 {

	public static void main(String[] args){
		//定义一个List，存放所有的项目对象
		IProject2 project =new Project2();

		//增加扭转时空项目
		project.add("扭转时空项目",100,10000000);

		//增加星球大战项目
		project.add("星球大战项目",10,100000);

		//增加超人改造项目
		project.add("超人改造项目", 1000, 100000000);

		//这边有100个项目
		for(int i=4;i<104;i++){
			project.add("第"+i+"个项目", i*5, i*1000000);

		}

		//遍历一下ArrayList，把所有的数据都取出
		IProjectIterator projectIterator =project.iterator();
		while(projectIterator.hasNext()){
			IProject2 p =(IProject2) projectIterator.next();
			System.out.println(p.getProjectInfo());
		}
	}
}
