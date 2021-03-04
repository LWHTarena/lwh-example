package design_pattern.state.scene01.advance;

public class StoppingState extends LiftState {

	@Override
	public void close() {
		//  do nothing

	}

	@Override
	public void open() {
		super.context.setLiftState(Context.openningState);
		super.context.getLiftState().open();

	}

	@Override
	public void run() {
		super.context.setLiftState(Context.runningState);
		super.context.getLiftState().run();

	}

	@Override
	public void stop() {
		System.out.println("电梯停止了...");

	}

}