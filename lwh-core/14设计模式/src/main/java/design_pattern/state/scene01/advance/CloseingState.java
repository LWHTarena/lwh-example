package design_pattern.state.scene01.advance;

public class CloseingState extends LiftState {

	@Override
	public void close() {
		System.out.println("电梯门关闭...");

	}

	@Override
	public void open() {
		super.context.setLiftState(Context.openningState);
		super.context.getLiftState().open();

	}

	@Override
	public void run() {//电梯门关了门就跑
		super.context.setLiftState(Context.runningState);//置为门敞开状态
		super.context.getLiftState().run();

	}

	@Override
	public void stop() {//电梯门关着，我不按楼层
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();

	}

}

