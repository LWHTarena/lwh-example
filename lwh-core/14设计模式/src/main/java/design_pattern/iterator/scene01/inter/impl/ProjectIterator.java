package design_pattern.iterator.scene01.inter.impl;

import design_pattern.iterator.scene01.inter.IProject2;
import design_pattern.iterator.scene01.inter.IProjectIterator;

import java.util.ArrayList;

/*定义一个迭代器*/
public class ProjectIterator implements IProjectIterator {

	//所有的项目都放在这里
	private ArrayList<IProject2> projectList =new ArrayList<IProject2>();

	private int currentItem =0;

	//构造函数传入projectList
	public ProjectIterator(ArrayList<IProject2> projectList){
		this.projectList =projectList;
	}

	@Override
	public boolean hasNext() {//判断是否还有元素，必须实现
		//定义一个返回值
		boolean b =true;
		if(this.currentItem>=projectList.size()||
				this.projectList.get(this.currentItem)==null){
			b =false;
		}
		return b;
	}

	@Override
	public Object next() {//获得下一个值
		return (IProject2)this.projectList.get(this.currentItem++);
	}

	@Override
	public void remove() {//删除一个对象
		// 暂时没有使用到

	}

}