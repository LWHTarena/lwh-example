package com.lwhtarena.array;

import org.junit.Test;

/**
 * @author liwh
 * @Title: TestArray
 * @Package com.lwhtarena.array
 * @Description:
 * @Version 1.0.0
 * @date 2020/6/21 15:34
 */
public class TestArray {

    /**
     * 定义一个int型数组
     * **/
    @Test
    public void test01(){
        /**开辟了一个长度为3的数组**/
        int data[] = new int[3];

        data[0] = 10; // 第一个元素
        data[1] = 20; // 第二个元素
        data[2] = 30; // 第三个元素
        for(int x = 0; x < data.length; x++) {
            System.out.println(data[x]); //通过循环控制索引
        }
    }

    /**
     * 采用分步的模式开辟数组空间
     */
    @Test
    public void test02(){
        int data[] = null;
        /**开辟了一个长度为3的数组**/
        data = new int[3];

        data[0] = 10; // 第一个元素
        data[1] = 20; // 第二个元素
        data[2] = 30; // 第三个元素
        for(int x = 0; x < data.length; x++) {
            System.out.println(data[x]); //通过循环控制索引
        }
    }

    /**
     *  采用静态初始化定义数组
     */
    @Test
    public void test03(){
        int data[] = {1, 2, 4, 545, 11, 32, 13131, 4444};
        for(int i = 0; i < data.length; i++) {
            System.out.println(data[i]);
        }
    }

    /**
     * 定义一个二维数组
     */
    @Test
    public void test04(){
        /**此时的数组并不是一个等列数组**/
        int data[][] = new int[][] {
                {1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
        /**
         * 如果在进行输出的时候一定要使用双重循环，
         * 外部的循环控制输出的行数，而内部的循环控制输出列数
         * **/
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                System.out.print("data[" + i + "][" + j + "]=" + data[i][j] + "、");
            }
            System.out.println();
        }
        /**由于输出麻烦，所以可以忽略了，在进行开发之中，出现二位数组的几率并不高。**/
    }
}
