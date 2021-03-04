package design_pattern.state.scene01.common;
/*模拟电梯运作*/
public class Client {

	public static void main(String[] args) {
		Ilift lift =new Lift();

		lift.setState(Ilift.STOPPING_STATE);

		lift.open();

		lift.close();

		lift.run();

		lift.stop();

	}

}