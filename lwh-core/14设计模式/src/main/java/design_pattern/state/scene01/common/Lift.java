package design_pattern.state.scene01.common;


public class Lift implements Ilift {

	private int state;
	public void setState(int state){
		this.state =state;
	}


	@Override
	public void close() {
		switch(this.state){
			case OPENING_STATE:
				this.closeWithoutLogic();
				this.setState(CLOSING_STATE);
				break;
			case CLOSING_STATE:
				//do nothing
				break;
			case RUNNING_STATE:
				//do nothing
				break;
			case STOPPING_STATE:
				//do nothing
				break;

		}

	}


	@Override
	public void open() {
		switch(this.state){
			case OPENING_STATE:
				//do nothing
				break;
			case CLOSING_STATE:
				this.openWithoutLogic();
				this.setState(OPENING_STATE);
				break;
			case RUNNING_STATE:
				//do nothing
				break;
			case STOPPING_STATE:
				this.openWithoutLogic();
				this.setState(OPENING_STATE);
				break;

		}

	}

	@Override
	public void run() {
		switch(this.state){

			case OPENING_STATE:
				//do nothing
				break;
			case CLOSING_STATE:
				this.runWithoutLogic();
				this.setState(RUNNING_STATE);
				break;
			case RUNNING_STATE:
				//do nothing
				break;
			case STOPPING_STATE:
				this.runWithoutLogic();
				this.setState(RUNNING_STATE);
		}

	}

	@Override
	public void stop() {
		switch(this.state){

			case OPENING_STATE:
				//do nothing
				break;
			case CLOSING_STATE:
				this.stopWithoutLogic();
				this.setState(CLOSING_STATE);
				break;
			case RUNNING_STATE:
				this.stopWithoutLogic();
				this.setState(CLOSING_STATE);
				break;
			case STOPPING_STATE:
				//do nothing
				break;
		}

	}


	//纯粹的电梯关门，不考虑实际逻辑
	private void closeWithoutLogic() {
		System.out.println("电梯门关闭...");

	}

	private void openWithoutLogic() {
		System.out.println("电梯门打开...");

	}
	private void stopWithoutLogic() {
		System.out.println("电梯停止了...");

	}

	private void runWithoutLogic() {
		System.out.println("电梯上下跑起来...");

	}


}
