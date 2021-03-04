package com.lwhtarena.选择题;

public class $_9 {

	/**
	 * @param args
	 * 如果你用“&”替换“&&”将发生什么错误？
	 * 答：会抛出空指针异常；&&会短路，只要遇到 boolean 值为 false
	 * 就不会再往后执行了；而&则是会把两边的运算都执行完。
	 */
	public static void main(String[] args) {
		String a=null;
		if (a!=null && a.length()>10) {

			System.out.println(a);
		}else{
			System.out.println("为空");
		}

	}

}
