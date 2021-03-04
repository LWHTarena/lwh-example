package design_pattern.builder.scene01.model;
/*
 * 宝马模型
 */
public class BMWModel extends CarModel {

	@Override
	protected void alarm() {
		System.out.println("BMWModel.alarm()...宝马的喇叭声音是这个样子的");

	}

	@Override
	protected void engineBoom() {
		System.out.println("BMWModel.engineBoom()...宝马的引擎是这个声音的");

	}

	@Override
	protected void start() {
		System.out.println("BMWModel.start()...宝马跑起来是这样子的");

	}

	@Override
	protected void stop() {
		System.out.println("BMWModel.stop()...宝马应该是这样停车的");

	}

}
