package com.lwhtarena.常见面试题;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 *
 * 写一个排序算法  1-100 随机数字  进行排序  要求效率。
 */
public class Sort {
    //选择排序方法
    public static void selectionSort(int[] number){
        for(int i=0;i<number.length;i++){
            int m =1;
            for(int j=i+1;j<number.length-1;j++){
                if(number[j]<number[m]){
                    m=j;
                }
                if(i!=m){
                    swap(number,i,m);
                }
            }
        }
    }
    //用于交换数组中的索引为 i、j 的元素
    private static void swap(int[] number, int i, int j) {
        int t;
        t = number[i];
        number[i] = number[j];
        number[j] = t;

    }
    public static void main(String[] args) {
        //定义一个数组
        int[] num = new int[100];
        for(int i=0;i<num.length;i++){
            num[i]=(int)(Math.random()*100)+1;

        }
        // 排序
        selectionSort(num);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }
    }
}
