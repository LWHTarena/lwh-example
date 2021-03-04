package design_pattern.bridge.scene01.abs.impl;


import design_pattern.bridge.scene01.abs.Product;

public class Clothes extends Product {

	@Override
	public void beProducted() {
		System.out.println("Clothes.beProducted()...生产出来的衣服是这样子的");

	}

	@Override
	public void beSelled() {
		System.out.println("Clothes.beSelled()...生产出来的衣服卖出去了");
	}

}