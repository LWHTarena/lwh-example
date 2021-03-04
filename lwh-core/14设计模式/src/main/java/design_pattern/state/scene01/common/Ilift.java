package design_pattern.state.scene01.common;
/*定义一个电梯*/
public interface Ilift {

	//电梯的四个状态
	public final static int OPENING_STATE =1;//门敞状态
	public final static int CLOSING_STATE =2;//门闭状态
	public final static int RUNNING_STATE =3;//运行状态
	public final static int STOPPING_STATE =4;//停止状态

	//设置电梯的状态
	public void setState(int state);

	public void open();

	public void close();

	public void run();

	public void stop();

}

