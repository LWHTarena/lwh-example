package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.Handler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Husband2 extends Handler {

	//丈夫只处理妻子的请求
	public Husband2(){
		super(2);
	}

	//丈夫的答复
	@Override
	public void response(IWomen women) {
		System.out.println("-------妻子向丈夫请示-------");
		System.out.println(women.getRequest());
		System.out.println("丈夫的答复是：同意\n");

	}



}