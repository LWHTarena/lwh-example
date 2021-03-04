package com.lwhtarena.常见面试题;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * 请写出求 n！的算法。
 */
public class Factorial {
    public static long doFactorial(long n) {
        if (n < 1) {
            System.out.println("ERROR");
            return 0;
        } else if (n == 1 || n  == 2)  {
            return n;
        } else {
            return n * doFactorial(n -  1);
        }
    }

    public static void main(String[] args) {
        long n = 6;
        System.out.println(doFactorial(n));
    }
}
