package com.lwhtarena.string;

/**
 * @author liwh
 * @Title: Test
 * @Package com.lwhtarena.string
 * @Description:
 * @Version 1.0.0
 * @date 2020/9/14 15:40
 */
public class Test {

    @org.junit.Test
    public void test01(){
        String str1 ="abc";
        String str2 =new String("abc");
        String str3 =str2.intern();
        System.out.println(str1 ==str2);
        System.out.println(str2 ==str3);
        System.out.println(str1 ==str3);
        System.out.println(str1.equals(str2));

        System.out.println("======================");
        String a =new String("abc").intern();
        String b =new String("abc").intern();
        if(a==b){
            System.out.println("a==b");
        }
    }
}
