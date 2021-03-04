package design_pattern.command.scene01.abstraction;

/**【需求组】
 * 需求组的职责就是和客户谈定需求，这个组的
 * 人应该都是业务领域专家
 */
public class RequirementGroup extends Group {

	//客户要求需求组过去和他们谈
	@Override
	public void find() {
		System.out.println("RequirementGroup.find()...找到需求组");
	}

	//客户要求增加一项需求
	@Override
	public void add() {
		System.out.println("RequirementGroup.add()...客户要求增加一项需求");
	}

	//客户要求修改一项需求"
	@Override
	public void change() {
		System.out.println("RequirementGroup.change()...客户要求修改一项需求");
	}

	//客户要求删除一项修改需求
	@Override
	public void delete() {
		System.out.println("RequirementGroup.delete()...客户要求删除一项修改需求");
	}

	//客户要求变更计划
	@Override
	public void plan() {
		System.out.println("RequirementGroup.plan()...客户要求变更计划");
	}

}

