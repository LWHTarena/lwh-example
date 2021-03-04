package com.lwhtarena.线程.thread.demo01;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:
 * <p>多线程多个锁:</p>
 * <p>关键字synchronized取得的锁都是对象锁，而不是把一段代码（方法）当做锁，
 * 所以代码中哪个线程先执行synchronized关键字的方法，哪个线程就持有该方法所
 * 属对象的锁（Lock），在静态方法上加synchronized关键字，表示锁定.class类，
 * 类一级别的锁（独占.class类）。
 * </p>
 * @version:v1.0
 * Created by Administrator on 2017/1/24.
 */
public class MultiThread {

    private static int num =0;

    public static synchronized void printNum(String tag){
        try {
            if(tag.equals("a")){
                num =100;
                System.out.println("tag a,set num over!");
                Thread.sleep(1000);
            }else {
                num =200;
                System.out.println("tag b,set num over!");
            }
            System.out.println("tag "+tag+",num ="+num);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //注意观察run方法输出顺序
    public static void main(String[] args) {

        //两个不同的对象
        final MultiThread m1 =new MultiThread();
        final MultiThread m2 =new MultiThread();

        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 =new Thread(new Runnable() {
            @Override
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();
    }

    /**
     产生的结果：
     tag a,set num over!
     tag b,set num over!
     tag b,num =200
     tag a,num =100

      因为 m1 和 m2 是俩对象，拥有自己对应的锁，当各自对象调用printNum（）方法时，是不会出现相斥现象，不需要共享printNum（）。

     倘若想先运行完m1的printNum（）再执行m2的printNum（），可以在调用方法（printNum（））上添加static
     变为静态，把synchronized上升到类级别上，那么就会共享printNum（）。

     结果如下：
     tag a,set num over!
     tag a,num =100
     tag b,set num over!
     tag b,num =200

     */
}
