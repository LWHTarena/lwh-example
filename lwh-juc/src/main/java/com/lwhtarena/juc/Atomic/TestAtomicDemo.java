package com.lwhtarena.juc.Atomic;

/**
 * @author liwh
 * @Title: TestAtomicDemo
 * @Package com.lwhtarena.juc
 * @Description:
 *
 * <pre>
 * // 改进: 使用原子变量
 * class AtomicDemo implements Runnable{
 *
 *     private AtomicInteger serialNumber = new AtomicInteger();
 *
 *     public void run(){
 *         try{
 *             Thread.sleep(200);
 *         }catch(InterruptedException e){
 *
 *         }
 *
 *         System.out.println(Thread.currentThread().getName()+":"+getSerialNumber());
 *     }
 *
 *     public int getSerialNumber(){
 *         // 自增运算
 *         return serialNumber.getAndIncrement();
 *     }
 * }
 * </pre>
 * @Version 1.0.0
 * @date 2020/6/17 01:57
 */
public class TestAtomicDemo{
    public static void main(String[] args){

        AtomicDemo ad = new AtomicDemo();

        for(int i=0; i < 10; i++){
            new Thread(ad).start();
        }
    }
}