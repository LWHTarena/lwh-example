package com.lwhtarena.选择题;

public class $_1 {
	static int x =10;
	static {x+=5;}
	public static void main(String[] args){
		System.out.println("x="+x);
	}
	static{x/=3;}
	/**
	 * 5和9行都可编译通过，执行结果为：x=5
	 * 自由块是类加载的时候就会被执行到的，自由块的执行顺序是按照在类中出现的先后顺序执行。
	 */
}
