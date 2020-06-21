package com.lwhtarena.juc.Lock;

/**
 * @author liwh
 * @Title: Ticket
 * @Package com.lwhtarena.juc.lock
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 16:58
 */
public class Ticket implements Runnable{
    private int tick = 100;

    @Override
    public void run(){
        while(true){
            if(tick > 0){
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){

                }

                System.out.println(Thread.currentThread().getName()+"完成售票,余票为: "+ --tick);
            }
        }
    }
}
