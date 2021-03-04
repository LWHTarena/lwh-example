package com.lwhtarena.选择题;
/**
 * 静态变量是所有对象所共享的，所以上述代码中的几个对象操作是同一静态变量  x，
 *   静态变量可以通过类名调用。
 * @author liwenhao
 *
 */

public class $_2 {
	//	private static final int y =100;
	private static  int x =100;
	public static void main(String[] args) {
		$_2 hs1 =new $_2();
		hs1.x++;
		System.out.println(x);
		$_2 hs2 =new $_2();
		hs2.x++;
		System.out.println(x);
		hs1 =new $_2();
		hs1.x++;
		System.out.println(x);
		$_2.x--;

		System.out.println("x="+x);


	}

}
