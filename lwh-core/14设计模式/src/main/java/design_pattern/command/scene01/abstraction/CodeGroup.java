package design_pattern.command.scene01.abstraction;
/**
 * 【代码组】
 * 代码组的职责就是实现业务逻辑，当然包括数据库设计了
 */
public class CodeGroup extends Group {

	@Override
	public void find() {
		System.out.println("CodeGroup.find()...找到代码组");

	}

	@Override
	public void add() {
		System.out.println("CodeGroup.add()...客户要求增加一项功能");
	}

	@Override
	public void change() {
		System.out.println("CodeGroup.change()...客户要求修改一项功能");
	}

	@Override
	public void delete() {
		System.out.println("CodeGroup.delete()...客户要求删除一项功能");

	}


	@Override
	public void plan() {
		System.out.println("CodeGroup.plan()...客户要求代码变更计划");
	}

}