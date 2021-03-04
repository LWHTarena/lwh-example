package com.lwhtarena.线程;

/**编程题：
 * 线程 A 向队列 Q 中不停写入数据，
 * 线程 B 从队列 Q 中不停读取数据（只要 Q 中有数据）。
 * 如何解答：
 *     接口中有两个:一个是向队列中写 push 方法  一个是从队列中读。
 * @author liwenhao
 *
 */
public class SafeStack  implements StackInterface {

	private int top=0;
	private int[] values =new int[10];
	private boolean dataAvailable =false;

	@Override
	public int[] pop() {
		synchronized (this) {
			while(!dataAvailable){//3
				try {
					wait();
				} catch (InterruptedException e) {
					// 忽略  //4
				}
			}
			System.out.println("弹出");
			top--;
			int[] test ={values[top],top};
			dataAvailable =false;
			//唤醒正在等待压入数据的线程
			notifyAll();
			return test;

		}
//		return null;
	}

	@Override
	public void push(int n) {
		synchronized (this) {//1
			while(dataAvailable){
				try{
					wait();
				}catch(InterruptedException e){
					//忽略//2
				}
			}
			values[top] =n;
			System.out.println("压入数字" + n + "步骤 1 完成");
			top++;
			dataAvailable = true;
			notifyAll();
			System.out.println("压入数字完成");
		}

	}

}
