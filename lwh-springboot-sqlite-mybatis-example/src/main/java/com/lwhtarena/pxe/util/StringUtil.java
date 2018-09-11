package com.lwhtarena.pxe.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @class StringUtil 
 * 
 * @description <p>类的详细说明</p> 
 * 
 * @version
 * <p> V1.0		2016-4-20		caohb</p>
 * <p> V1.1		yyyy-mm-dd		name	modify</p>
 * 
 */
public class StringUtil {
	
	/**
	 * 是否为空
	 * 
	 * @param v
	 * @return
	 * @since 2015-07-25
	 * @author caohb
	 */
	public static boolean isNull(Object v) {
		return v == null || "".equals(v.toString().trim());
	}

	/**
	 * 是否非空
	 * 
	 * @param v
	 * @return
	 * @since 2015-07-25
	 * @author caohb
	 */
	public static boolean isNotNull(Object v) {
		return !isNull(v);
	}

	/**
	 * 去除字符串两边的空格
	 * 
	 * @param v
	 * @return
	 * @author caohb
	 * @since 2015-07-25
	 */
	public static String trimNull(Object v) {
		return (v == null ? "" : v.toString().trim());
	}
	
	/**
	 * 返回一个定长的随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandom(int length) {
		String allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static boolean isNotEmpty(String str){
		boolean bool =true;
		if(str ==null||str.equals("")||str ==""){
			bool =false;
		}
		return bool;
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");

	}
	
	/**
	 * 返回固定长度的流水号
	 * @param currNum	当前数值
	 * @param numFormat	要求返回的数值字符串格式
	 * @return
	 */
	public static String getFormatNum(int currNum, String numFormat) {
		DecimalFormat df = new DecimalFormat(numFormat);
		return df.format(currNum);
	}
	
	/**
	 * 返回固定长度的流水号
	 * @param currNum	当前数值
	 * @param numFormat	要求返回的数值字符串格式
	 * @return
	 */
	public static String getFormatNum(long currNum, String numFormat) {
		DecimalFormat df = new DecimalFormat(numFormat);
		return df.format(currNum);
	}
	
	/**
	 * 全角转半角
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String QBchange(String QJstr) throws Exception {
		if (QJstr == null)
			return null;

		char[] c = QJstr.toCharArray();

		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}

		return new String(c);
	}
	
	/**
	 * 判断是否是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null || "".equals(str.trim()) || "null".equals(str)){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2) {
		/*if (version1 == null || version2 == null) {
			throw new Exception("参数为空");
		}*/
		String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
		String[] versionArray2 = version2.split("\\.");
		int idx = 0;
		int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
		int diff = 0;
		while (idx < minLength
				&& (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
				&& (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
			++idx;
		}
		//如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
		diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
		return diff;
	}
	
	public static void main(String[] args) {
		
		//System.out.println(getUUID());
		System.out.println(compareVersion("v3.0","v3.0"));
		
		/*System.out.println(DateUtil.parse("2015-11-18 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime());
		System.out.println(DateUtil.parse("2015-11-18 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime());
		System.out.println(System.currentTimeMillis());
		
		Date currDate = DateUtil.parse("2014-11-22", "yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.add(Calendar.DATE, -1);
		String lastDate = DateUtil.format(cal.getTime(), "yyyy-MM-dd");
		System.out.println(lastDate);
		
		System.out.println(DateUtil.format(new Timestamp(1451100312587L), "yyyy-MM-dd HH:mm:ss"));
		
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		if (p.matcher("比例").find()) {
			System.out.println(":::::::::::::::有中文:::::::::::::::");
		}*/
		System.out.println(isMac("A1:D2:3C:45:69:7B"));
	}

	/**
	 * 判断是否是IP
	 * @param addr
	 * @return
	 */
	public static boolean isIp(String addr) {
//		"[0-255].[0-255]";
		if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
		{
			return false;
		}
		/**
		 * 判断IP格式和范围
		 */
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(addr);
		return  mat.find();
    }

	public static boolean isMac(String macAddr) {
		String rexp = "[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}:[A-Fa-f0-9]{2}";
		Pattern pat = Pattern.compile(rexp);
		Matcher mat = pat.matcher(macAddr);
		return  mat.find();
	}
}
