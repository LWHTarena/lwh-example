package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.IHandler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Son implements IHandler {

	//目前想儿子请示
	@Override
	public void handleMessage(IWomen women) {
		System.out.println("母亲的请示是："+women.getRequest());
		System.out.println("儿子的答复：同意");
	}

}

