package com.lwhtarena.选择题;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 如何格式化日期
 * @author liwenhao
 *变量 formatNow 就是格式化好的日期。
 */
public class $_8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String formatNow=sdf.format(now);
		System.out.println(formatNow);
	}

}
