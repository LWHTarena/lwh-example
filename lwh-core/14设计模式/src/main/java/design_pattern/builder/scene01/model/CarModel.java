package design_pattern.builder.scene01.model;

import java.util.ArrayList;

/**
 * 建造者模型【Builder Pattern】
 * @author liwenhao
 * create 时间：2013-12-31
 *
 *  定义一个车辆模型的抽象类，所有的车辆模型都继承这里类
 */
public abstract class CarModel {

	//这个参数是各个基本方法执行的顺序
	private ArrayList<String> sequence =
			new ArrayList<String>();

	protected abstract void start();

	protected abstract void stop();

	protected abstract void alarm();

	protected abstract void engineBoom();

	public final void run(){
		//循环一边，谁在前，就执行谁
		for(int i=0;i<this.sequence.size();i++){
			String actionName =this.sequence.get(i);

			if(actionName.equalsIgnoreCase("start")){
				this.start();
			}
			if(actionName.equalsIgnoreCase("stop")){
				this.stop();
			}
			if(actionName.equalsIgnoreCase("alarm")){
				this.alarm();
			}
			if(actionName.equalsIgnoreCase("engine boom")){
				this.engineBoom();
			}
		}

	}

	//把传递过来的值传递到类内
	final public void setSequence(ArrayList<String> sequence){
		this.sequence =sequence;
	}

}
