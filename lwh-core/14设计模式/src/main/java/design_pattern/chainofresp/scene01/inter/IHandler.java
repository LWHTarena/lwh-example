package design_pattern.chainofresp.scene01.inter;

public interface IHandler {//父系社会，那就是男性有至高无上的权利，handler控制权

	//一个女性要求逛街，你要处理者个问题
	public void handleMessage(IWomen women);
}
