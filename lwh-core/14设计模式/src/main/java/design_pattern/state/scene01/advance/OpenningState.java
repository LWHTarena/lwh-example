package design_pattern.state.scene01.advance;

/**
 * 在电梯开启的状态能做什么事情
 * @author liwenhao
 *
 */
public class OpenningState extends LiftState {

	@Override
	public void close() {
		//状态修改
		super.context.setLiftState(Context.closeingState);
		//动作委托为CloseState来执行
		super.context.getLiftState().close();

	}

	@Override
	public void open() {
		System.out.println("电梯门开启...");

	}

	@Override
	public void run() {
		//do nothing

	}

	@Override
	public void stop() {
		//do nothing

	}

}
