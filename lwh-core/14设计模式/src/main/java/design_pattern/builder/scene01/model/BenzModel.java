package design_pattern.builder.scene01.model;
/*
 * 奔驰模型
 */
public class BenzModel extends CarModel {

	@Override
	protected void alarm() {
		System.out.println("BenzModel.alarm()...奔驰的喇叭声音是这个样子的");

	}

	@Override
	protected void engineBoom() {
		System.out.println("BenzModel.engineBoom()...奔驰的引擎是这个声音的");

	}

	@Override
	protected void start() {
		System.out.println("BenzModel.start()...奔驰跑起来是这样子的");

	}

	@Override
	protected void stop() {
		System.out.println("BenzModel.stop()...奔驰应该是这样停车的");

	}

}
