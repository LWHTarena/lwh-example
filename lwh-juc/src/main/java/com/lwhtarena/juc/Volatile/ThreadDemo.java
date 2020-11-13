package com.lwhtarena.juc.Volatile;

/**
 * @author liwh
 * @Title: ThreadDemo
 * @Package com.lwhtarena.juc.Volatile
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 16:56
 */
public class ThreadDemo implements Runnable{
    private boolean flag = false;
    /**使用volatile关键字解决问题--解决内存不可见性**/
//    private volatile boolean flag = false;

    @Override
    public void run(){
        try{
            // 该线程 sleep(200), 导致了程序无法执行成功
            Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        flag = true;

        System.out.println("flag="+isFlag());
    }

    public boolean isFlag(){
        return flag;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }
}
