package design_pattern.command.scene01.command;

public class AddRequirementCommand extends Command {

	//执行增加一项需求的命令
	@Override
	public void execute() {
		super.rg.find();

		super.rg.add();

		super.pg.add();

		super.cg.add();

		super.rg.plan();

	}

}
