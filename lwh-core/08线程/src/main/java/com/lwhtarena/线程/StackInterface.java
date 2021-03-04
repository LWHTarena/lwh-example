package com.lwhtarena.线程;

/**
 * 线程 A 向队列 Q 中不停写入数据，
 * 线程 B 从队列 Q 中不停读取数据（只要 Q 中有数据）。
 * 如何解答：
 *     接口中有两个:一个是向队列中写 push 方法  一个是从队列中读。
 * @author liwenhao
 *
 */
public interface StackInterface {
	public void push(int n);
	public int[] pop();
}

