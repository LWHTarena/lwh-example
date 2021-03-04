package design_pattern.composite.scene01.inter;

/*叶子节点，也就是最小的小兵了，只能自己干活，不能指派别人了*/
public interface ILeaf {

	//获得自己的信息
	public String getInfo();

}

