package design_pattern.state.scene01.advance;


public class RunningState extends LiftState {

	@Override
	public void close() {
		//do nothing

	}

	@Override
	public void open() {
		//do thing

	}

	@Override
	public void run() {
		System.out.println("电梯上下跑...");

	}

	@Override
	public void stop() {
		super.context.setLiftState(Context.stoppingState);
		super.context.getLiftState().stop();

	}

}