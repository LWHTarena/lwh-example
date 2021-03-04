package design_pattern.observer.scene01.advance;

/**观察者模式【Observer Pattern】
 * 所有被观察者，通用接口
 * @author liwenhao
 * 这是一个通用的被观察接口，所有的被观察者都可以实现这个接口
 *
 */
public interface Observable {

	//增加一个观察者
	public void addObserver(Observer observer);

	//删除一个观察者
	public void deleteObserver(Observer observer);

	//既然要观察，我发生改变了她也应该有所动作--通知观察者
	public void notifyObservers(String context);
}

