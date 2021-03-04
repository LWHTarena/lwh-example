package design_pattern.composite.scene01.inter;

import java.util.ArrayList;

/*定义一个节点，就为总经理服务*/
public interface IRoot {

	//得到总经理的信息
	public String getInfo();

	//总经理下边要有小兵，那要能增加小兵，比如研发部经理，这个是树的节点
	public void add(IBranch branch);

	//那要能增加树叶节点
	public void add(ILeaf leaf);

	//既然能增加，那要还能够遍历，不可能总经理不知道她手下有哪些人
	public ArrayList getSubordinateInfo();

}

