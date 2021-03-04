package design_pattern.prototype.scene01.advance;
/**
 * 这个模式的简单仅次于单例模式和迭代模式
 * @author liwenhao
 *
 */
public class AdvTemplate {
	//广告信名称
	private String advSubject ="xx银行国庆信用卡抽奖活动";

	//广告信内容
	private String advContext ="国庆抽奖活动通知：只要刷卡就送你1百万...";

	//取得广告信的名称
	public String getAdvSubject() {
		return this.advSubject;
	}

	//取得广告信的内容
	public String getAdvContext() {
		return this.advContext;
	}


}
