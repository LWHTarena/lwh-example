package com.lwhtarena.常见面试题;

/**
 * @author lwh
 * @folder com.lwhtarena.常见面试题
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 *
 * 冒泡排序:依次比较相邻的两个数,将大数放在前面,小数放在后面。第一趟结束,在最后
 * 的数必是所有数中的最小数。重复以上过程,直至最终完成排序。由于在排序过程中总是
 * 大数往前放,小数往后放,相当于气泡往上升,所以称作冒泡排序。请用 JAVA 语言编写一
 * 个完成冒泡排序算法的程序。
 */
public class bubbleSortTest {
    int[] bubbleSort(int before[]) {
        int t;
        for (int i = 0; i < before.length; i++) {
            for (int j = 0; j < before.length  -  i -  1; j++) {
                if (before[j] > before[j + 1]) {
                    t = before[j];
                    before[j] = before[j +  1];
                    before[j + 1] = t;
                }
            }
        }
        return before;
    }

    public static void main(String[] args) {

    }
}
