package com.lwhtarena.juc.Volatile;

/**
 * @author liwh
 * @Title: TestVolatile
 * @Package com.lwhtarena.juc
 * @Description: 未使用 volatile 之前，flag 为什么不能共享
 * volatile利用内存栅栏机制来保持变量的一致性。不能代替锁，其只具备数据可见性一致性，不具备原子性。
 *
 * <pre>
 * // 解决问题方式一: 同步锁
 * // 但是,效率太低
 * public class TestVolatile{
 *     public static void main(String[] args){
 *         ThreadDemo td = new ThreadDemo();
 *         new Thread(td).start();
 *         while(true){
 *             // 使用同步锁
 *             synchronized(td){
 *                 if(td.isFlag()){
 *                     System.out.println("########");
 *                     break;
 *                 }
 *             }
 *         }
 *     }
 * }
 *
 * // 解决方式二: 使用 volatile 关键字修饰
 * public class TestVolatile{
 *     public static void main(String[] args){
 *         ThreadDemo td = new ThreadDemo();
 *         new Thread(td).start();
 *         while(true){
 *             if(td.isFlag()){
 *                 System.out.println("########");
 *                 break;
 *             }
 *         }
 *     }
 * }
 *
 * class ThreadDemo implements Runnable{
 *     private volatile boolean flag = false;
 *     同上(略)
 * }
 *
 * </pre>
 *
 * @Version 1.0.0
 * @date 2020/6/17 00:53
 */
public class TestVolatile {
    public static void main(String[] args){
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while(true){
            if(td.isFlag()){
                System.out.println("####-------------####");
                break;
            }
        }
    }
}