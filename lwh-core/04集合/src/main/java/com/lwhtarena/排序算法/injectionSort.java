package com.lwhtarena.排序算法;

/**
 * 插入排序
 * @author lwh
 * @folder com.lwhtarena.算法
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class injectionSort {
    public static void main(String[] args) {

        int[] number ={314,298,508,123,486,145};

        // 第一个元素作为一部分，对后面的部分进行循环
        for (int j = 1; j < number.length; j++) {
            int tmp = number[j];
            int i = j - 1;
            while (tmp < number[i]) {
                number[i + 1] = number[i];
                i--;
                if (i == -1)
                    break;
            }
            number[i + 1] = tmp;


            for(int a:number){
                System.out.println(a);
            }
        }

    }
}
