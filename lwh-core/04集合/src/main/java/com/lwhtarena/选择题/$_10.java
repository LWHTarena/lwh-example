package com.lwhtarena.选择题;

/**
 * 用递归方法实现正序显示数组元素。例如 String[] s = {“a”,”b”,”c”,”d”};
 方法声明如下：
 void print(String[] s,int i){  }
 * @author liwenhao
 *
 */
public class $_10 {
	void print(String[] s,  int i) {
		if ((i >= 0) && (i < s.length)) {
			System.out.print(s[i]);
			i++;
			print(s, i);
		}
	}

	public static void main(String args[]){
		String[] s = {"l","i","w","e","n","h","a","o"};
		$_10 a =new $_10();
		a.print(s, 3);
	}
}
