package design_pattern.composite.scene01.advance;

import java.util.ArrayList;

/**这些下边有小兵或者是经理等风云人物*/
public interface IBranch extends ICorp{

	//能够增加小兵（树叶节点）或者是经理（树叶节点）
	public void addSubordinate(ICorp crop);

	//我还要能够获得下属的信息
	public  ArrayList<ICorp> getSubordinate();
	/*本来还应该有一个方法就做subordinate（ICrop crop），删除下属
	 * 这个方法没有用到就不写了
	 *
	 */


}