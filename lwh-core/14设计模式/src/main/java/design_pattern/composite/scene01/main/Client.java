package design_pattern.composite.scene01.main;

import design_pattern.composite.scene01.inter.IBranch;
import design_pattern.composite.scene01.inter.ILeaf;
import design_pattern.composite.scene01.inter.IRoot;
import design_pattern.composite.scene01.inter.impl.Branch;
import design_pattern.composite.scene01.inter.impl.Leaf;
import design_pattern.composite.scene01.inter.impl.Root;

import java.util.ArrayList;

/*Client的作用是组装这可树，并遍历一遍*/
/**这个程序比较长，如果在我们的项目中有这样的程序，肯定是被拉出来做典型的，以后维护起来很困难
 * 因此，程序是要短小精悍的，故这棵树是有问题的
 *
 */
public class Client {

	public static void main(String[] args){

		//首先产生了一个根节点
		IRoot ceo =new Root("李文浩","总经理",1000000);

		//产生三个部门经理，也是树枝节点
		IBranch developDep =new Branch("周星星", "研发部门经理", 100000);
		IBranch salesDep =new Branch("成龙", "销售部门经理", 150000);
		IBranch financeDep =new Branch("刘德华", "财务部门经理", 200000);

		//再把三个小组长产生出来
		IBranch firstDevGroup =new Branch("梁朝伟", "开发一组组长", 5000);
		IBranch secondDevGroup =new Branch("周润发", "开发二组组长", 6000);

		//剩下的及时我们这些小兵了，就是路人甲，路人乙
		ILeaf a =new Leaf("a", "开发人员", 2000);
		ILeaf b =new Leaf("b", "开发人员", 2000);
		ILeaf c =new Leaf("c", "开发人员", 2000);
		ILeaf d =new Leaf("d", "开发人员", 2000);
		ILeaf e =new Leaf("e", "开发人员", 2000);
		ILeaf f =new Leaf("f", "开发人员", 2000);
		ILeaf g =new Leaf("g", "销售人员", 4000);
		ILeaf h =new Leaf("h", "销售人员", 5000);
		ILeaf i =new Leaf("i", "财务人员", 4000);
		ILeaf j =new Leaf("j", "财务人员", 5000);
		ILeaf k =new Leaf("k", "CEO秘书", 8000);
		ILeaf zhengLaoLiu =new Leaf("郑老六", "研发部副总", 20000);

		/*该产生的人都产生出来了，然后我们怎么组装这棵树
		 * 首先是定义总经理下有三个部门经理*/
		ceo.add(developDep);
		ceo.add(salesDep);
		ceo.add(financeDep);
		//总经理下的还有一个秘书
		ceo.add(k);

		//定义研发部门下的结构
		developDep.add(firstDevGroup);
		developDep.add(secondDevGroup);
		//研发部门经理下还有一个副总
		developDep.add(zhengLaoLiu);

		//看看开发两个小组下有什么
		firstDevGroup.add(a);
		firstDevGroup.add(b);
		firstDevGroup.add(c);
		secondDevGroup.add(d);
		secondDevGroup.add(e);
		secondDevGroup.add(f);

		//再看看销售部门下的人员情况
		salesDep.add(h);
		salesDep.add(i);

		//最后一个财务
		financeDep.add(j);

		//树状结构写完毕，然后我们打印出来
		System.out.println(ceo.getInfo());

		//打印出来整个树形
		getAllSubordinateInfo(ceo.getSubordinateInfo());
	}



	//遍历所有的树节点，打印出信息
	private static void getAllSubordinateInfo(ArrayList subordinateList) {

		int length =subordinateList.size();
		for(int m=0;m<length;m++){
			Object s =subordinateList.get(m);
			if(s instanceof Leaf){//是个叶子节点，也就是员工
				ILeaf employee =(ILeaf) s;
				System.out.println(((Leaf) s).getInfo());
			}else{
				IBranch branch =(IBranch) s;
				System.out.println(branch.getInfo());
				//再递归调用
				getAllSubordinateInfo(branch.getSubordinateInfo());
			}
		}
	}

}
