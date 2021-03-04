package design_pattern.observer.scene01.advance;

public class LiWenhao implements Observer {

	@Override
	public void update(String str) {
		System.out.println("李文浩：观察到韩非子活动，自己也开始行动了");
		this.cry(str);
		System.out.println("李文浩：韩非子要死了吗\n");
	}

	private void cry(String context) {
		System.out.println("李文浩：因为："+context+"--所以我悲伤");
	}

}
