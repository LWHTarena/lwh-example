package design_pattern.chainofresp.scene01.inter.impl;

import design_pattern.chainofresp.scene01.inter.IHandler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Husband implements IHandler {

	//妻子向丈夫请示
	@Override
	public void handleMessage(IWomen women) {
		System.out.println("妻子的请示是："+women.getRequest());
		System.out.println("丈夫的答复是：同意");
	}

}

