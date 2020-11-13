package com.lwhtarena.juc.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liwh
 * @Title: HelloWord
 * @Package com.lwhtarena.juc.concurrent
 * @Description:
 * @Version 1.0.0
 * @date 2020/11/13 11:56
 */
public class HelloWordThread implements Runnable{

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static CopyOnWriteArrayList<String> list =new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        System.out.println("=======集合list："+list.size());

        while (it.hasNext()){
            System.out.println(Thread.currentThread().getName()+" :-- "+it.next());
            list.add("AAA");
        }
    }
}
