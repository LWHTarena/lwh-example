package design_pattern.chainofresp.scene01.inter;
/**
 * 责任链模式【Chain of Responsibility Pattern】
 * @author liwenhao
 *
 * 古代悲哀女性的总称
 */
public interface IWomen {

	//获得个人状况
	public int getType();

	//获得个人请示，你要干什么？出去逛街？约会？还是看电影
	public String getRequest();
}
