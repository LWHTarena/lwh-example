package com.lwhtarena.选择题

����;
/**
 * ��д���� n�����㷨��
 * @author liwenhao
 *
 */
public class Factorial {
	public static void main(String[] args) {

		long n = 6;
		System.out.println(doFactorial(n));
	}

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
}
