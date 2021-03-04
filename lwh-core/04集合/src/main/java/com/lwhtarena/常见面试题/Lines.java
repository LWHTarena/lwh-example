package com.lwhtarena.常见面试题;

import java.util.Scanner;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 *  * 下面是一个由*号组成的 4 行倒三角形图案。要求：1、输入倒三角形的行数，行数的取值 3-21 之
 * 间，对于非法的行数，要求抛出提示“非法行数！”；2、在屏幕上打印这个指定了行数的倒三角形。
 *               *******
                 *****
                 ***
                 *
 */
public class Lines {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        if (lines > 3 && lines  < 21) {
            for (int i = lines-1; i >= 0; i--) {
                for (int z = 0; z <= i  * 2; z++) {
                    System.out.print("*");
                }
                System.out.print("\n");
            }
        }else{
            System.out.println("非法行数！");
        }

    }
}
