package com.lwhtarena.线程;

public class PushThread implements Runnable{

	private StackInterface s;

	public PushThread(StackInterface s){
		this.s =s;
	}

	@Override
	public void run() {
		int i =0;
		while(true){
			java.util.Random r =new java.util.Random();
			i =r.nextInt(10);
			s.push(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

	}

}

