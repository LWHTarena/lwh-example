package design_pattern.state.scene01.advance;

/**
 * 定义一个接口
 * @author liwenhao
 *
 */
public abstract class LiftState {

	//定义一个环境角色，也就是封装状态的变换引起的功能变化
	protected Context context;

	public void setContext(Context _context){
		this.context =_context;
	}

	//设置电梯的状态
	public abstract void  open();

	public abstract void close();

	public abstract void run();

	public abstract void stop();
}
