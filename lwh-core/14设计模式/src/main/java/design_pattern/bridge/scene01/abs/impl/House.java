package design_pattern.bridge.scene01.abs.impl;


import design_pattern.bridge.scene01.abs.Product;

public class House extends Product {

	@Override
	public void beProducted() {
		System.out.println("House.beProducted()...生产出的房子是这样子的");

	}

	@Override
	public void beSelled() {
		System.out.println("House.beSelled()...生产出来的房子总得卖出去呀Client.java");

	}

}
