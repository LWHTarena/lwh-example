package com.lwhtarena.juc.lockdemo;
import java.util.concurrent.*;

public class DeadLockDemo {
    public static void main(String[] args) {
        String LockA = "A锁";
        String LockB = "B锁";
        ExecutorService threadPool =
                new ThreadPoolExecutor(2, 2, 1L,
                        TimeUnit.SECONDS, new LinkedBlockingDeque<>(2), Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPool.execute(() -> {
            new HoldThread(LockA, LockB).run();
        });
        threadPool.execute(() -> {
            new HoldThread(LockB, LockA).run();
        });
    }
}

class HoldThread implements Runnable {
    private String LockA;
    private String LockB;

    public HoldThread(String LockA, String LockB) {
        this.LockA = LockA;
        this.LockB = LockB;
    }

    @Override
    public void run() {
        synchronized (LockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有：" + LockA + "\t 尝试获取：" + LockB);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持 有：" + LockB + "\t尝试获取：" + LockA);
            }
        }
    }
}