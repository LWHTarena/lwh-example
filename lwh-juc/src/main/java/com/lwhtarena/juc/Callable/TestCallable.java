package com.lwhtarena.juc.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author liwh
 * @Title: TestCallable
 * @Package com.lwhtarena.juc
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/17 03:03
 */
public class TestCallable{
    public static void main(String[] args){

        ThreadDemo td = new ThreadDemo();

        // 执行 Callable 方式,需要 FutureTask 实现类的支持
        // FutureTask 实现类用于接收运算结果, FutureTask 是 Future 接口的实现类
        FutureTask<Integer> result = new FutureTask<Integer>((Callable<Integer>) td);

        new Thread(result).start();

        // 接收线程运算后的结果
        try{
            // 只有当 Thread 线程执行完成后,才会打印结果;
            // 因此, FutureTask 也可用于闭锁
            Integer sum = result.get();
            System.out.println(sum);
        }catch(InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}