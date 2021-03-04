package design_pattern.chainofresp.scene01.inter.impl;


import design_pattern.chainofresp.scene01.inter.IHandler;
import design_pattern.chainofresp.scene01.inter.IWomen;

public class Father implements IHandler {

	//未出嫁女儿请示父亲
	@Override
	public void handleMessage(IWomen women) {
		System.out.println("女儿的请示是："+women.getRequest());
		System.out.println("父亲的答复是：同意");
	}
}
