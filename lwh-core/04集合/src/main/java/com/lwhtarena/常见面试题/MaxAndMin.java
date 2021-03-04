package com.lwhtarena.常见面试题;

import java.util.Scanner;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */

/**
 * 编写一个字符界面的 Java Application  程序，接受用户输入的 10 个整数，
 * 并输出这 10 个整数的最大值和最小值。
 * 采用了冒泡排序进行排序
 */
public class MaxAndMin {
    public static void main(String[] args) {
        Scanner scanner  =
                new Scanner(System.in);
        int[] arr =new int[10];
        for(int i =0;i<arr.length;i++){
            int next =scanner.nextInt();
            arr[i] =next;
        }
//		int[] after =Arrays.sort(arr);
//		System.out.println("最小值："+after[0]+",最大值："+after[arr.length-1]);
    }
}
