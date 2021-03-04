package design_pattern.composite.scene01.advance优化;

import java.util.ArrayList;

/**组合模式【Composite Pattern】也叫合成模式，有时又叫做部分--整体模式【Part-Whole】
 * 主要是用来描述整体与部分的关系，用的最多的地方就是树形结构
 * 组装这个树形结构，并展示出来
 */
public class Client {

	public static void main(String[] args) {

		//首先是组装有一个组织结构出来
		Branch ceo =CompositeCorpTree();

		//首先把CEO的信息打印出来
		System.out.println(ceo.getInfo());

		//然后是把所有的员工信息打印出来
		System.out.println(getTreeInfo(ceo));
	}

	//把整个树组装出来
	private static Branch CompositeCorpTree() {
		//首先产生总经理
		Branch root =new Branch("李文浩","总经理",1000000);
		//产生三个部门经理，也是树枝节点
		Branch developDep =new Branch("周星星", "研发部门经理", 100000);
		Branch salesDep =new Branch("成龙", "销售部门经理", 150000);
		Branch financeDep =new Branch("刘德华", "财务部门经理", 200000);

		//再把三个小组长产生出来
		Branch firstDevGroup =new Branch("梁朝伟", "开发一组组长", 5000);
		Branch secondDevGroup =new Branch("周润发", "开发二组组长", 6000);

		//剩下的及时我们这些小兵了，就是路人甲，路人乙
		Leaf a =new Leaf("a", "开发人员", 2000);
		Leaf b =new Leaf("b", "开发人员", 2000);
		Leaf c =new Leaf("c", "开发人员", 2000);
		Leaf d =new Leaf("d", "开发人员", 2000);
		Leaf e =new Leaf("e", "开发人员", 2000);
		Leaf f =new Leaf("f", "开发人员", 2000);
		Leaf g =new Leaf("g", "销售人员", 4000);
		Leaf h =new Leaf("h", "销售人员", 5000);
		Leaf i =new Leaf("i", "财务人员", 4000);
		Leaf j =new Leaf("j", "财务人员", 5000);
		Leaf k =new Leaf("k", "CEO秘书", 8000);
		Leaf zhengLaoLiu =new Leaf("郑老六", "研发部副总", 20000);

		//开始组装
		//CEO下有三个部门经理和一个秘书
		root.addSubordinate(k);
		root.addSubordinate(developDep);
		root.addSubordinate(salesDep);
		root.addSubordinate(financeDep);

		//研发部经理
		developDep.addSubordinate(zhengLaoLiu);
		developDep.addSubordinate(firstDevGroup);
		developDep.addSubordinate(secondDevGroup);

		//看看开发两个小组下有什么
		financeDep.addSubordinate(a);
		financeDep.addSubordinate(b);
		financeDep.addSubordinate(c);
		secondDevGroup.addSubordinate(d);
		secondDevGroup.addSubordinate(e);
		secondDevGroup.addSubordinate(f);

		//在看看销售部下的人员情况
		salesDep.addSubordinate(h);
		salesDep.addSubordinate(i);

		//最后一个财务
		financeDep.addSubordinate(j);
		return root;
	}

	//遍历整棵树，只要给我节点，我就能遍历出所有的节点
	private static String getTreeInfo(Branch root) {
		ArrayList<Corp> subordinateList =root.getSubordinate();
		String info ="";
		for(Corp s :subordinateList){//是员工就直接获得信息
			if(s instanceof Leaf){
				info =info +s.getInfo()+"\n";

			}else{//是一个小头目
				info =info +s.getInfo()+"\n"+getTreeInfo((Branch)s);

			}

		}
		return info;
	}

}
