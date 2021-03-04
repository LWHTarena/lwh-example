package com.lwhtarena.string;

import org.junit.Test;

/**
 * @author liwh
 * @Title: TestString
 * @Package com.lwhtarena.string
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 15:21
 */
public class TestString {

    /**
     * String 不可被继承 -底层final
     *
     * String类是不可变类，即一旦一个String对象被创建以后，包含在这个对象中的字符序列是不可改变的，直至这个对象被销毁。
     *
     * StringBuffer是线程安全的，而StringBuilder则没有实现线程安全功能，所以性能略高。
     */
    @Test
    public void test01(){
        String str ="测试string";
        System.out.println("str:"+str);

        System.out.println("====================================================");

        StringBuffer stringBuffer =new StringBuffer();
        for(int i=0;i<5;i++){
            stringBuffer.append("您好:"+i);
        }
        System.out.println(stringBuffer);

        System.out.println("====================================================");

        StringBuilder builder = new StringBuilder("123");
        builder.append("456");
        System.out.println(builder);



    }
}
