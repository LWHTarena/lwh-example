package com.lwhtarena.线程;

/**
 * 读线程
 * @author liwenhao
 *
 */
public class PopThread implements Runnable{

	private StackInterface s;
	public PopThread(StackInterface s){
		this.s =s;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("->"+ s.pop()[0] + "<-");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}

	}

}

