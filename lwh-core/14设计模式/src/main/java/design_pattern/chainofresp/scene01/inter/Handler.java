package design_pattern.chainofresp.scene01.inter;

public abstract class Handler {//父系社会，那就是男性有至高无上的权利，handler控制权

	//能处理的级别
	private int level =0;

	//责任传递，下一个人责任人是谁？
	private Handler nextHandler;

	public Handler(int _level){
		this.level =_level;
	}

	//一个女性要求逛街，你要处理者个问题
	public final void handleMessage(IWomen women){
		if(women.getType() ==this.level){
			this.response(women);
		}else{
			if(this.nextHandler !=null){//有后续环节，才有请求往后递送
				this.nextHandler.handleMessage(women);
			}else{
				System.out.println("--------没地方请示了，不做处理！---------\n");
			}
		}
	}

	//有请示那当然要回应
	public abstract void response(IWomen women);

	/*
	 * 如果属于你的处理的返回，你应该让她找一下个环节的人，比如
	 * 女儿出嫁了，还向父亲请示是否可以逛街，那父亲就应该告诉女儿，应该找丈夫请示
	 */
	public void setNext(Handler _handler){
		this.nextHandler =_handler;
	}
}
