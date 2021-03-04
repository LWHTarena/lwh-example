package design_pattern.composite.scene01.advance;
/**leaf是树叶节点，在这里就是我们这些小兵*/
public class Leaf implements ICorp {

	private String name ="";

	private String position ="";

	private int salary =0;

	public Leaf(String name,String position,int salary){
		this.name =name;
		this.position =position;
		this.salary =salary;
	}

	@Override
	public String getInfo() {//获得信息
		String info ="";
		info ="名称："+this.name;
		info =info+"\t职位："+this.position;
		info =info+"\t薪水："+this.salary;
		return info;
	}

}
