package design_pattern.bridge.scene01.abs.impl;

import design_pattern.bridge.scene01.abs.Product;

public class IPod extends Product {

	@Override
	public void beProducted() {
		System.out.println("IPod.beProducted()...生产出的Ipod是这个样子的");
	}

	@Override
	public void beSelled() {
		System.out.println("IPod.beSelled()...生产出来的ipod卖出去啦");
	}

}
