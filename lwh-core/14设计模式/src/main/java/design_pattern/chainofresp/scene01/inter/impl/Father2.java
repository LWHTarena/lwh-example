package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.Handler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Father2 extends Handler {

	//父亲只处理女儿的请求
	public Father2(){
		super(1);
	}

	//父亲的答复
	@Override
	public void response(IWomen women) {
		System.out.println("-------女儿向父亲请示-------");
		System.out.println(women.getRequest());
		System.out.println("父亲的答复是：同意\n");

	}



}