package com.lwhtarena.spring.aop;

/**
 * @author liwh
 * @Title: MathCalculator
 * @Package com.lwhtarena.spring.aop
 * @Description:
 * @Version 1.0.0
 * @date 2020/5/23 08:53
 */
public class MathCalculator {
    public int div(int i,int j){
        System.out.println("MathCalculator...div...");
        return i/j;
    }
}
