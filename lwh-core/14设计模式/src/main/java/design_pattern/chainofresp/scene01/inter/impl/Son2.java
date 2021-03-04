package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.Handler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Son2 extends Handler {

	//儿子只处理母亲的请求
	public Son2(){
		super(3);
	}

	//儿子的答复
	@Override
	public void response(IWomen women) {
		System.out.println("-------母亲向儿子请示-------");
		System.out.println(women.getRequest());
		System.out.println("儿子的答复是：同意\n");

	}



}