package com.lwhtarena.线程.设计模式;

public class TemplateMethod {

    public final void  print(String message){
        System.out.println("########################");
        wrapPrint(message);
        System.out.println("########################");
    }

    protected void wrapPrint(String message) {

    }


    public static void main(String[] args) {
        TemplateMethod tm1 =new TemplateMethod(){

            @Override
            protected void wrapPrint(String message) {
                System.out.println("************"+message+"*************");
            }
        };

        //模拟new Tread的start（）方法
        tm1.print("李文浩，您好");


        TemplateMethod tm2 =new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("^^^^^^^^^^^^^"+message+"^^^^^^^^^^^");
            }
        };

        tm2.print("线程模板设计模式");

        TemplateMethod tm3 =new TemplateMethod();
        tm3.print("少年包青天");
    }
}
