package com.lwhtarena.juc.thread;

import java.util.concurrent.*;

/**
 * @author liwh
 * @Title: ThreadTest
 * @Package com.lwhtarena.juc.thread
 * @Description: 线程初始化的4种方式
 * @Version 1.0.0
 * @date 2020/9/29 15:24
 */
public class ThreadTest {

    public static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /**
         *  1)继承Thread
         *  2)实现Runnable接口
         *  3)实现Callable接口+FutureTask（可以拿到返回结果、可以处理异常）
         *  4)线程池
         *
         * 区别：
         *  1、2不能得到返回值，3可以获取返回值
         *  1、2、3都不能控制资源
         *  4可以控制资源，性能稳定
         */

        /**1)继承Thread**/
        Thread001 thread001 =new Thread001();
        thread001.start();

        /**2)实现Runnable接口**/
        Runnable002 thread002 =new Runnable002();
        new Thread(thread002).start();

        /**3)实现Callable接口+FutureTask（可以拿到返回结果、可以处理异常）*/
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable003());
        new Thread(futureTask).start();
        /**阻塞，等待整个线程执行完成，获取返回结果**/
        Integer integer = futureTask.get();

        /**
         * 我们以后在业务代码里面，以上三种启动线程的方式都不用，【将所有的多线程异步任务都交给线程池执行】。
         */
        /*new Thread(()-> System.out.println("hello")).start();*/

        /**
         * 当前系统中，池只有一两个（不宜多），每个异步任务，提交给线程池让他自己去执行就行了
         * 4)线程池
         * **/
//        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new Runnable002());
        service.submit(futureTask);


    }

    /**
     * 1)继承Thread
     */
    public static class Thread001 extends Thread{
        @Override
        public void run(){
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i =10/2;
            System.out.println("运行结果："+i);
        }
    }

    /**
     * 2)实现Runnable接口
     */
    public static class Runnable002 implements Runnable{
        @Override
        public void run(){
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i =10/2;
            System.out.println("运行结果："+i);
        }
    }

    /**
     * 3)实现Callable接口+FutureTask（可以拿到返回结果、可以处理异常）
     */
    public static class Callable003 implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            System.out.println("当前线程："+Thread.currentThread().getId());
            int i =10/2;
            System.out.println("运行结果："+i);
            return i;
        }
    }
}
