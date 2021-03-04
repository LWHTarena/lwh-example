package design_pattern.observer.scene01.advance;
/**
 * 所有观察者，通用接口
 * @author liwenhao
 *
 */
public interface Observer {

	//一发现别人有动静，自己也要行动起来
	public void update(String context);
}