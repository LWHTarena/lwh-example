package com.lwhtarena.pxe.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * 日期工具操作类
 * @class DateUtil 
 * 
 * @description <p>类的详细说明</p> 
 * 
 * @version
 * <p> V1.0		2016-4-20		caohb</p>
 * <p> V1.1		yyyy-mm-dd		name	modify</p>
 * 
 */
public class DateUtil {


	public static String DEF_DATE = "yyyy-MM-dd";
	public static String DEF_TIME = "HH:mm:ss";
	public static String CUR_DATE = "yyyyMMdd";
	public static String SHORT_DATE = "yyMMdd";
	public static String DEF_DATE_TIME = DEF_DATE + " " + DEF_TIME;

	// 使用ThreadLocal缓存线程创建的SimpleDateFormat日期格式器
	private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

	/**
	 * 获取该线程缓存的SimpleDateFormat日期格式器
	 * 
	 * @return
	 */
	public static SimpleDateFormat getDateFormat() {
		SimpleDateFormat sdf = threadLocal.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat(DEF_DATE);
			threadLocal.set(sdf);
		}
		return sdf;
	}

	/**
	 * 使用指定的格式来格式化日期对象
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式字符串
	 * @return 格式化日期字符串
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = getDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 使用指定的格式来解析日期字符串
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            格式字符串
	 * @return 日期对象
	 */
	public static Date parse(String dateStr, String pattern) {
		//如果为null或空串,直接返回null
		if (StringUtil.isNull(dateStr)) {
			return null;
		}
		
		SimpleDateFormat sdf = getDateFormat();
		Date d = null;
		try {
			sdf.applyPattern(pattern);
			d = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 返回以格式yyyy-MM-dd表示的日期
	 * 
	 * @return
	 */
	public static Date parseDefDate(String date) {
		return parse(date, DEF_DATE);
	}

	/**
	 * 返回以格式HH:mm:ss表示的时间
	 * 
	 * @return
	 */
	public static Date parseDefTime(String time) {
		return parse(time, DEF_TIME);
	}

	/**
	 * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间
	 * 
	 * @return
	 */
	public static Date parseDefDateTime(String dateTime) {
		return parse(dateTime, DEF_DATE_TIME);
	}

	/**
	 * 返回以格式yyyy-MM-dd表示的当前日期字符串
	 * 
	 * @return
	 */
	public static String getCurrentDefDate() {
		return format(new Date(), DEF_DATE);
	}

	/**
	 * 返回以格式yyyyMMdd表示的当前日期字符串
	 * 
	 * @return
	 */
	public static String getCurrentCurDate() {
		return format(new Date(), CUR_DATE);
	}

	/**
	 * 返回以格式HH:mm:ss表示的时间字符串
	 * 
	 * @return
	 */
	public static String getCurrentDefTime() {
		return format(new Date(), DEF_TIME);
	}

	/**
	 * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间字符串
	 * 
	 * @return
	 */
	public static String getCurrentDefDateTime() {
		return format(new Date(), DEF_DATE_TIME);
	}

	/**
	 * 返回以格式yyyy-MM-dd HH:mm:ss表示的日期时间字符串
	 * 
	 * @return
	 */
	public static String getDefDateTime(Date date) {
		return format(date, DEF_DATE_TIME);
	}

	/**
	 * 返回以格式yyMMdd表示的当前日期字符串
	 * 
	 * @return
	 */
	public static String getCurrentShorDate() {
		return format(new Date(), SHORT_DATE);
	}

	/**
	 * 获取带格式的时间戳字符串
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp() {
		return getCurrentTimestamp("yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取不带格式的时间戳字符串
	 * 
	 * @return
	 */
	public static String getUnCurrentTimestamp() {
		return getCurrentTimestamp("yyyyMMddHHmmssSSS");
	}

	/**
	 * 返回指定格式的当前时间戳
	 * 
	 * @param pattern
	 *            格式字符串
	 * @return
	 */
	public static String getCurrentTimestamp(String pattern) {
		SimpleDateFormat sdf = getDateFormat();
		sdf.applyPattern(pattern);
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 将指定日期转化为星期几
	 * @param date 
	 * @param format
	 * @return
	 */
	public static String getWeek(String date, String format){
		String[] day = new String[] { "日", "一", "二", "三", "四", "五", "六" };
		format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
		// 输入的日期格式必须是这种
		SimpleDateFormat sdf = getDateFormat();
		sdf.applyPattern(format);
		Scanner s = new Scanner(date);
		while (true) {
			try {
				Date d = sdf.parse(s.nextLine());// 把字符串转化成日期
				Calendar cal = convertCalendar(d);
				s.close();
				return date + " " + "星期" + day[cal.get(Calendar.DAY_OF_WEEK)-1];
			} catch (ParseException e) {
				
			}
		}
	}
	
	/**
	 * 将java.util.Date类型日期转换为java.util.Calendar类型日期
	 * 
	 * @param date
	 *            java.util.Date 类型日期
	 * @return java.util.Calendar 类型日期
	 */
	public static Calendar convertCalendar(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 将UTC时间转换为本地时间
	 * @param utcTime
	 * @param utcTimePatten
	 * @param localTimePatten
	 * @return
	 */
	public static String utc2Local(String utcTime, String utcTimePatten,
			String localTimePatten) {
		if (StringUtil.isNull(utcTime)) {
			return null;
		}
		
		SimpleDateFormat sdf = getDateFormat();
		//设置UTC时间格式及时区
		sdf.applyPattern(utcTimePatten);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date gpsUTCDate = null;
		try {
			gpsUTCDate = sdf.parse(utcTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//设置本地时间格式及时区
		sdf.applyPattern(localTimePatten);
		sdf.setTimeZone(TimeZone.getDefault());
		String localTime = null;
		if(gpsUTCDate!=null){
			localTime = sdf.format(gpsUTCDate.getTime());
		}
		return localTime;
	}
	
	/**
	 * 根据起始日期和间隔时间计算结束日期
	 * 
	 * @param sDate 开始时间
	 * 
	 * @param days 间隔时间
	 * 
	 * @return 结束时间
	 * */
	public static Date calculateEndDate(Date sDate, int years,int months,int days,int hours,int minutes,int seconds) {
		// 将开始时间赋给日历实例
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setTime(sDate);
		// 计算结束时间
		sCalendar.add(Calendar.YEAR, years); // 年份加、减  
		sCalendar.add(Calendar.MONTH, months);// 月份加、减
		sCalendar.add(Calendar.DATE, days);// 日期加、减
		sCalendar.add(Calendar.HOUR, hours);// 时加、减
		sCalendar.add(Calendar.MINUTE, minutes);// 分加、减
		sCalendar.add(Calendar.SECOND, seconds);// 秒加、减
		return sCalendar.getTime();
	}
	
	/**
	 * 比较两个Date类型的日期大小
	 * 
	 * @param sDate 开始时间
	 * 
	 * @param eDate 结束时间
	 * 
	 * @return result返回结果(0--相同 1--前者大 2--后者大)
	 * */
	private static int compareDate(Date sDate, Date eDate) {
		int result = 0;
		// 将开始时间赋给日历实例
		Calendar sC = Calendar.getInstance();
		sC.setTime(sDate);
		// 将结束时间赋给日历实例
		Calendar eC = Calendar.getInstance();
		eC.setTime(eDate);
		// 比较
		result = sC.compareTo(eC);
		// 返回结果
		return result;
	}
	
	/**
	 * 计算两个日期的时间间隔
	 * 
	 * @param sDate 开始时间
	 * 
	 * @param eDate 结束时间
	 * 
	 * @param type 间隔类型
	 *            ("Y/y"--年 "M/m"--月 "D/d"--日)
	 * 
	 * @return interval时间间隔
	 * */
	private static int calInterval(Date sDate, Date eDate, String type) {
		// 时间间隔，初始为0
		int interval = 0;

		/* 比较两个日期的大小，如果开始日期更大，则交换两个日期 */
		// 标志两个日期是否交换过
		boolean reversed = false;
		if (compareDate(sDate, eDate) > 0) {
			Date dTest = sDate;
			sDate = eDate;
			eDate = dTest;
			// 修改交换标志
			reversed = true;
		}

		/* 将两个日期赋给日历实例，并获取年、月、日相关字段值 */
		Calendar sCalendar = Calendar.getInstance();
		sCalendar.setTime(sDate);
		int sYears = sCalendar.get(Calendar.YEAR);
		int sMonths = sCalendar.get(Calendar.MONTH);
		int sDays = sCalendar.get(Calendar.DAY_OF_YEAR);

		Calendar eCalendar = Calendar.getInstance();
		eCalendar.setTime(eDate);
		int eYears = eCalendar.get(Calendar.YEAR);
		int eMonths = eCalendar.get(Calendar.MONTH);
		int eDays = eCalendar.get(Calendar.DAY_OF_YEAR);

		// 年
		if (cTrim(type).equals("Y") || cTrim(type).equals("y")) {
			interval = eYears - sYears;
			if (eMonths < sMonths) {
				--interval;
			}
		}
		// 月
		else if (cTrim(type).equals("M") || cTrim(type).equals("m")) {
			interval = 12 * (eYears - sYears);
			interval += (eMonths - sMonths);
		}
		// 日
		else if (cTrim(type).equals("D") || cTrim(type).equals("d")) {
			interval = 365 * (eYears - sYears);
			interval += (eDays - sDays);
			// 除去闰年天数
			while (sYears < eYears) {
				if (isLeapYear(sYears)) {
					--interval;
				}
				++sYears;
			}
		}
		// 如果开始日期更大，则返回负值
		if (reversed) {
			interval = -interval;
		}
		// 返回计算结果
		return interval;
	}
	
	/**
	 * 判定某个年份是否是闰年
	 * 
	 * @param year 待判定的年份
	 * 
	 * @return 判定结果
	 * */
	private static boolean isLeapYear(int year) {
		return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
	}
	
	/**
	 * 
	 * 字符串去除两头空格，如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
	 * 
	 * @param tStr 输入字符串
	 * 
	 * @return 如果为空，则返回""，如果不空，则返回该字符串去掉前后空格
	 * 
	 */
	public static String cTrim(String tStr) {
		String ttStr = "";
		if (tStr == null) {
		} else {
			ttStr = tStr.trim();
		}
		return ttStr;
	}

	/**
	 * 取两个日期相差天数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDistanceDays( String date1, String date2 ) {
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		Date one;
		Date two;
		long days = 0;
		try {
			one = df.parse( date1 );
			two = df.parse( date2 );
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if( time1 < time2 ) {
				diff = time2 - time1;
			}
			else {
				diff = time1 - time2;
			}
			days = diff / (1000 * 60 * 60 * 24);
		}
		catch( ParseException e ) {
			e.printStackTrace();
		}
		catch( Exception e2 ) {
			e2.printStackTrace();
		}
		return days;
	}
	
	/**
	 * 取得当前日期，不含时间
	 * 
	 * @return 当前日期，格式为：yyyy-MM-dd
	 */
	public static String getCurDate() {
		return formatDate( new Date() );
	}
	
	/**
	 * 格式化日期的表示
	 * 
	 * @param date 所要格式化的日期
	 * @return 返回以yyyy-MM-dd格式表示的日期
	 */
	public static String formatDate( Date date ) {
		if( null == date )
			return "";
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
		return df.format( date );
	}
	
	/**
	 * 根据时间命名文件
	 * @return
	 */
	public static String getFileDate(){
		SimpleDateFormat formate =new SimpleDateFormat("yyyyMMddHHmmss");
		return formate.format(new Date()).toString()+String.valueOf((int)Math.random()*100000)+".xva";
	}

	 /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
	public static void main(String[] args) {
//		System.out.println(getFileDate());
		
//		Date date1 = parse("2017-01-01","yyyy-MM-dd");
//		Date date2 = parse("2017-01-09","yyyy-MM-dd");
//		
//		System.out.println("==="+calInterval(date1,date2,"y"));
		
//		System.out.println("===当前时间："+ new Date());
//		System.out.println("===10分钟之前的时间："+calculateEndDate(new Date(),0,0,0,0,-10,0));
		
		/*try {
			System.out.println("==1=="+dateToStamp("2017-01-12 09:38:53"));
			System.out.println("==2=="+dateToStamp("2017-01-12 09:38:54"));
			System.out.println("==3=="+dateToStamp("2017-01-12 09:38:55"));
			System.out.println("==4=="+dateToStamp("2017-01-12 09:48:55"));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		//System.out.println("---1---"+stampToDate("1484185134000"));
		//System.out.println("---2---"+stampToDate("1484185205000"));
	}
}
