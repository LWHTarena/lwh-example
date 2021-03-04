package com.lwhtarena.线程.thread.demo02;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:<p>
 *  关键字synchronized拥有锁重入的功能，也就是在使用synchronized时，当一个线程得到了
 *  一个对象的锁后，再次请求时是可以再次得到该对象的锁。
 * </p>
 * @version:v1.0
 */
public class SyncDubbo02 {

    public static void main(String[] args) {
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                Sub sub =new Sub();
                sub.operationSub();
            }
        });

        t1.start();
    }

    private static class Sub extends Main{
        public synchronized void operationSub(){
            try {
                while (i>0){
                    i--;
                    System.out.println("Sub print i = " + i);
                    Thread.sleep(100);
                    this.operationSup();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static class Main {
        public int i =10;

        public synchronized void operationSup() {
            try {
                i--;
                System.out.println("Main print i = " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
