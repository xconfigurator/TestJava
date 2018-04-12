package cn.edu.hebau.liuyang.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK 8 之前的日期处理类
 * java.util.Date
 * java.text.SimpleDateFormat
 * java.util.Calendar(日期计算)
 * 
 * @author liuyang
 *
 */
public class TestDateJDK8Before {
	
	public static void main(String[] args) throws ParseException {
		System.out.println("#TestDate##############################");
		testDate();
		System.out.println("#TestSimpleDateFormat##################");
		testSimpleDateFormat();
		System.out.println("#TestClaendar##########################");
		testCalendar();
	}
	
	public static void testDate() {
		// 最简单的使用
		Date d1 = new Date();
		System.out.println(d1);
		
		// 转换：long --> Date	Date(long)
		long curr = System.currentTimeMillis();
		Date d2 = new Date(curr);
		System.out.println(d2);
		
		// 转换：Date --> long	getTime()
		System.out.println(d2.getTime());
	}
		
	public static void testSimpleDateFormat() throws ParseException {
		// 格式化字符串参考API文档
		// https://docs.oracle.com/javase/10/docs/api/java/text/SimpleDateFormat.html
		// 不带毫秒的格式化字符串：	yyyy-MM-dd HH:mm:ss
		// 带毫秒的格式化字符串：		yyyy-MM-dd HH:mm:ss.SSS
		final String PATTERN = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		
		// 转换：Date --> String format
		System.out.println(sdf.format(new Date()));
		
		// 转换：Sting --> Date	 parse
		//final String STR = "2018-4-13 03:15";// 不可接受
		//final String STR = "2018-04-13 03:15"; // 不可接受
		//final String STR = "2018-04-13 03:15:00"; // OK
		final String STR = "2018-4-13 3:15:00"; // OK
		System.out.println(sdf.parse(STR));
	}
	
	// 可以使用Calendar进行日期计算
	public static void testCalendar() {
		// 最简单的使用
		Calendar cal = Calendar.getInstance();
		StringBuffer buf = new StringBuffer();
		buf.append(cal.get(Calendar.YEAR)).append("-");
		buf.append(cal.get(Calendar.MONDAY) + 1).append("-");
		buf.append(cal.get(Calendar.DAY_OF_MONTH)).append(" ");
		buf.append(cal.get(Calendar.HOUR_OF_DAY)).append(":");
		buf.append(cal.get(Calendar.MINUTE)).append(":");
		buf.append(cal.get(Calendar.SECOND));
		System.out.println(buf);
		
		// 日期计算
		// 问题：看三天以后的日期
		Calendar cal2 = Calendar.getInstance();
		StringBuffer buf2 = new StringBuffer();
		buf2.append(cal2.get(Calendar.YEAR)).append("-");
		buf2.append(cal2.get(Calendar.MONDAY) + 1).append("-");
		buf2.append(cal2.get(Calendar.DAY_OF_MONTH) + 3).append(" ");// 只需在这里加3即可
		buf2.append(cal2.get(Calendar.HOUR_OF_DAY)).append(":");
		buf2.append(cal2.get(Calendar.MINUTE)).append(":");
		buf2.append(cal2.get(Calendar.SECOND));
		System.out.println(buf2);
		System.out.println("==========");
	}

}
