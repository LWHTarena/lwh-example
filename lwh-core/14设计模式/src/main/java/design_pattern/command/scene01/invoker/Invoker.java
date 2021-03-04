package design_pattern.command.scene01.invoker;

import design_pattern.command.scene01.command.Command;

/**
 * 接头人
 * 接头人的职责就是接受命令，并执行
 *
 */
public class Invoker {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void action(){
		this.command.execute();
	}
}
