package design_pattern.observer.scene01.advance;

public class LiuSi implements Observer {

	@Override
	public void update(String str) {
		System.out.println("刘斯：观察到韩非子活动，开始想老板汇报...");
		this.reportToQinShiHuang(str);
		System.out.println("刘斯：真被乐死了\n");

	}

	private void reportToQinShiHuang(String context) {
		System.out.println("刘斯：因为"+context+"--所以我快乐");
	}

}
