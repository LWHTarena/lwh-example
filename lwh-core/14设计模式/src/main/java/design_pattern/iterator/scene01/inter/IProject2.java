package design_pattern.iterator.scene01.inter;
/**
 * 迭代器模式【Iterator Pattern】
 *定义一个接口，所有的 项目都是一个接口
 */
public interface IProject2 {

	//从老板这里看到的就是项目信息
	public String getProjectInfo();

	//增加项目
	public void add(String name,int num,int cost);

	//获得遍历一个可以被遍历的对象
	public IProjectIterator iterator();

}
