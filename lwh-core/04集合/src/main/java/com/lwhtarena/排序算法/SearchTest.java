package com.lwhtarena.排序算法;

/**
 * @author lwh
 * @folder com.lwhtarena.算法
 * @date 2016/10/9.
 * @版权 Copyright (c) 2016 lwhtarena.com. All Rights Reserved.
 */
public class SearchTest {
    /**被搜索数据的大小*/
    private static int size =5000000;
    public static void main(String[] args) {
        long[] data =new long[size];
        //添加测试数据
        for(int k=0;k<data.length;k++){
            data[k] =k;
        }

        //要测试的数据
        long target =4970002;
        binaryFindTest(data,target);
    }

    /**
     * 二分搜索算法实现
     * @param data  数据集合
     * @param target搜索的数据
     * return 返回找到的数据的位置，返回-1表示没有找到
     */
    public static int binaryFind(long[] data, long target) {
        int start =0;
        int end =data.length-1;
        while(start<=end){
            int middleIndex =(start+end)/2;
            if(target==data[middleIndex]){
                return middleIndex;
            }
            if(target>=data[middleIndex]){
                start =middleIndex+1;
            }else{
                end =middleIndex-1;
            }
        }
        return -1;

    }

    /**
     * 二分搜索测试
     * @param data  数据集合
     * @param target搜索的数据
     */
    public static void binaryFindTest(long[] data, long target){
        long start = System.nanoTime();
        int result = binaryFind(data, target);
        long end = System.nanoTime();
        System.out.println("binary search position："  + result);
        System.out.println("binary search time：" + (end  -  start));
    }
}
