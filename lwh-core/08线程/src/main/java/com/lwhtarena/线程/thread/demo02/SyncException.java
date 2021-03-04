package com.lwhtarena.线程.thread.demo02;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:<p>synchronized异常</p>
 * <p>出现异常，锁自动释放</p>
 * @version:v1.0
 */
public class SyncException {

    private int i =0;

    public static void main(String[] args) {
        final SyncException se =new SyncException();
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                se.operation();
            }
        },"t1");

        t1.start();
    }

    private synchronized void operation() {

        while (true){
            try {
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " , i = " + i);

                if(i==10){//模拟异常
                    Integer.parseInt("a");
                    //throw  new RuntimeException();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 出现异常，锁自动释放
     * 业务场景：
     *  对于web应用程序，异常释放锁的情况，如果不及时处理，很可能对你的应用程序业务
     *  逻辑产生严重的错误，比如你现在执行一个队列任务，很多对象都去在等待第一个对象
     *  正确执行完毕，就释放了锁，那么可想而知后续的对象执行的都是错误的逻辑。所以这
     *  一点一定要注意引起注意，在编写代码的时候，一定要考虑周全。
     */


}
