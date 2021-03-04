package com.lwhtarena.线程.thread.demo01;

/**
 * @author：liwenhao
 * @Date:2017/1/24
 * @description:<p>
 *     业务整体需要使用完整的synchronized，保持业务的原子性。
 * </p>
 *
 * @version:v1.0
 */
public class DirtyRead {
    private String username ="liwenhao";
    private String password ="123456";

    public synchronized void setValue(String username,String pwd){
        this.username =username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.password =pwd;

        System.out.println("setValue最终结果：username = " + username + " , password = " + password);
    }

    /*synchronized*/
    public synchronized void getValue(){
        System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dr =new DirtyRead();
        Thread t1 =new Thread(new Runnable() {
            @Override
            public void run() {
                dr.setValue("z3","456");
            }
        });

        t1.start();
        Thread.sleep(1000);

        dr.getValue();
    }
}
