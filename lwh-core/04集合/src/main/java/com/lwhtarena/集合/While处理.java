package com.lwhtarena.集合;

/**
 * @author lwh
 * @folder com.lwhtarena
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class While处理 {
    static int count=0;
    public static void main(String[] args) {
        while(true){
            try{
                if(count++ ==0){
                    throw new Exception();
                }
                System.out.println("No exception");
            }catch(Exception e){
                System.out.println("Exception thrown");
            }finally{
                System.out.println("in finally clause");
                if(count == 2) break; // out of "while
            }
        }
    }
}
