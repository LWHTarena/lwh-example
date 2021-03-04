package com.lwhtarena.线程.create;

@SuppressWarnings("all")
public class ProgrammerApp {

	public static void main(String[] args) {
		//1)、创建真实角色
		Programmer prp =new Programmer();

		//2)、创建代理角色+真实角色引用
		Thread proxy =new Thread(prp);

		//3)、调用 .start()启动线程
		proxy.start();

		for(int i=0;i<1000;i++){
			System.out.println("一边聊QQ...");
		}

	}

}
