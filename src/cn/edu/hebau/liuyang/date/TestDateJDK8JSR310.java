package cn.edu.hebau.liuyang.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author liuyang
 * 
 *         总结一下新API的统一风格： 1. 获取“现在”用now 2. 计算：plusXXX，minusXXX 3. 间隔：between
 */
public class TestDateJDK8JSR310 {
	public static void main(String[] args) throws InterruptedException {
		// 1. 本地日期 LocalDate LocalTime LocalDateTime
		System.out.println("####################################");
		test01Local();

		// 2. 时间戳（UTC） Instant
		System.out.println("####################################");
		test02Instant();
		// 2. 演示时间戳的一些不恰当的调用方法
		System.out.println("####################################");
		test02InstantPlus();

		// 3. 间隔 Duration 时间
		System.out.println("####################################");
		test03Duration();
		// 4. 间隔 Period 日期
		System.out.println("####################################");
		test04Period();

		// 5. 时间校正器 TemporalAdjuster TemporalAdjusters
		// 需求：“将日期调整到下个周日”
		System.out.println("####################################");
		test05TemporalAdjuster();

		// 6. 格式化 DateTimeFormatter
		System.out.println("####################################");
		test06DateTimeFormatter();

		// 7. 时区 ZonedDate ZonedTime ZoneDateTime
		System.out.println("####################################");
		test07Zoned();

		// 8. java.util.Date --> LocalDateTime
		// 思路：通过时间戳	getTime() 和 Instant
		System.out.println("####################################");
		test08JavaUtilDate2LocalDateTime();
	}

	// 8. java.util.Date --> LocalDateTime
	// 思路：通过时间戳	getTime() 和 Instant
	private static void test08JavaUtilDate2LocalDateTime() {
		java.util.Date d = new java.util.Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(d.getTime()),
				ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);
	}

	// 1. 本地日期类 LocalDate LocalTime LocalDateTime
	public static void test01Local() {
		// 现在
		LocalDateTime ldt = LocalDateTime.now(); // 所在地区的
		System.out.println(ldt);

		// 指定时间
		LocalDateTime ldt2 = LocalDateTime.of(2018, 4, 14, 22, 40, 44);
		System.out.println(ldt2);

		// 日期计算，体现“不可变”，这个有点像Scala中的immutable XX
		System.out.println(ldt2.plusDays(2)); // 打印的是新实例的内容
		System.out.println(ldt2.plusDays(-2)); // 打印的是新实例的内容
		System.out.println(ldt2); // 验证
		System.out.println(ldt2.minusYears(2)); // 打印的是新实例的内容
		System.out.println(ldt2.minusYears(-2)); // 打印的是新实例的内容
		System.out.println(ldt2); // 验证

		// get
		System.out.println(ldt2.getYear()); // 2018
		System.out.println(ldt2.getMonth()); // APRIL Month对象
		System.out.println(ldt2.getMonthValue()); // 4
		System.out.println(ldt2.getDayOfMonth()); // 14
		System.out.println(ldt2.getDayOfWeek()); // SATURDAY DayOfWeek对象
		System.out.println(ldt2.getDayOfYear()); // 104
		System.out.println(ldt2.getHour()); // 22
		System.out.println(ldt2.getMinute()); // 40
		System.out.println(ldt2.getSecond()); // 44
		System.out.println(ldt2.getNano()); // 0
	}

	// 2. Instant:时间戳（以Unix元年：1970-01-01T00:00:00.000 为起点 ）
	public static void test02Instant() {
		// 现在
		Instant inst = Instant.now(); // UTC Coordinated Universal Time 通过原子钟，以GMT为基准。
		System.out.println(inst); // e.g. 2018-04-14T15:32:11.792Z

		// 加上时区偏移量
		OffsetDateTime odt = inst.atOffset(ZoneOffset.ofHours(8));
		System.out.println(odt); // e.g. 2018-04-14T23:32:11.792+08:00

		// 时间戳
		long timestamp = inst.toEpochMilli();
		System.out.println(timestamp);

		// 从1970-01-01T00:00:00.000开始算
		System.out.println(Instant.ofEpochMilli(1000)); // 1970-01-01T00:00:01Z
	}

	/**
	 * 3. 间隔 Duration Duration : 计算两个“时间”之间的间隔 <-- Period : 计算两个“日期”之间的间隔
	 * 
	 * @throws InterruptedException
	 */
	public static void test03Duration() throws InterruptedException {

		// Duration 时间戳
		Instant inst1 = Instant.now();
		TimeUnit.SECONDS.sleep(1);
		Instant inst2 = Instant.now();

		System.out.println(Duration.between(inst1, inst2));
		System.out.println(Duration.between(inst1, inst2).getSeconds()); // 注意getSeconds，后面都是to
		System.out.println(Duration.between(inst1, inst2).toDays());
		System.out.println(Duration.between(inst1, inst2).toHours());
		System.out.println(Duration.between(inst1, inst2).toMinutes());
		System.out.println(Duration.between(inst1, inst2).toMillis()); // 可以观察到方法调用时间
		System.out.println(Duration.between(inst1, inst2).toNanos()); // 可以观察到方法调用时间

		// Duration 时间
		LocalTime lt1 = LocalTime.now();
		TimeUnit.SECONDS.sleep(1);
		LocalTime lt2 = LocalTime.now();

		System.out.println(Duration.between(lt1, lt2));
		System.out.println(Duration.between(lt1, lt2).getSeconds());
	}

	/**
	 * 4. 间隔 Period Duration : 计算两个“时间”之间的间隔 Period : 计算两个“日期”之间的间隔 <--
	 */
	public static void test04Period() {
		LocalDate ld1 = LocalDate.of(2018, 2, 22);
		LocalDate ld2 = LocalDate.now();

		System.out.println(Period.between(ld1, ld2)); // P1M24D 一个月零24天
		System.out.println(Period.between(ld1, ld2).getDays()); // 24
		System.out.println(Period.between(ld1, ld2).getMonths()); // 1
		System.out.println(Period.between(ld1, ld2).getYears()); // 0
		System.out.println(Period.between(ld1, ld2).toTotalMonths()); // 1
	}

	/**
	 * 5. TemporalAdjuster TemporalAdjusters
	 * 通过TemporalAdjusters获取常用TemporalAdjuster的实例。 也可以自定义实现TemporalAdjuster
	 * 
	 * 需求：“将日期调整到下个周日” 需求：“下一个工作日”
	 */
	public static void test05TemporalAdjuster() {

		// 通过TemporalAdjusters获取常用TemporalAdjuster的实例。
		// 下一个周日
		LocalDate nextSunday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(LocalDate.now());
		System.out.println(nextSunday);

		// 自定义实现TemporalAdjuster
		// 下一个工作日
		LocalDate nextWorkday = LocalDate.now().with((l) -> {
			LocalDate ld = (LocalDate) l;
			DayOfWeek dow = ld.getDayOfWeek();
			if (dow.equals(DayOfWeek.FRIDAY)) {
				return ld.plusDays(3);
			} else if (dow.equals(DayOfWeek.SATURDAY)) {
				return ld.plusDays(2);
			} else {
				return ld.plusDays(1);
			}
		});
		System.out.println(nextWorkday);
	}

	// 6. 格式化 DateTimeFormatter
	public static void test06DateTimeFormatter() {
		LocalDateTime ldt = LocalDateTime.now();

		// 使用默认格式
		System.out.println(ldt.format(DateTimeFormatter.ISO_DATE));
		System.out.println(ldt.format(DateTimeFormatter.ISO_DATE_TIME));

		// 自定义格式
		DateTimeFormatter stf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

		// 1. 格式化
		System.out.println(ldt.format(stf));
		// 2. 解析字符串
		System.out.println(LocalDate.parse("2018/04/15", stf));
		// System.out.println(LocalDateTime.parse("2018/04/15", stf)); // 注意，字符串信息不足
		// 2. 解析字符串， 通过DateTimeFormatter
		// System.out.println(stf.parse("2018/4/15")); // 注意是MM
		System.out.println(stf.parse("2018/04/15"));
	}

	// 7. 时区 ZonedDate ZonedTime ZoneDateTime
	public static void test07Zoned() {
		// 看看可用时区
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.stream().sorted().forEach(System.out::println);

		// 第一种方式
		// 美国西部时间 US/Eastern
		System.out.println(LocalDateTime.now(ZoneId.of("US/Eastern"))); // LocalDateTime
		// 输出：2018-04-14T14:40:34.252

		// 第二种方式
		System.out.println(LocalDateTime.now().atZone(ZoneId.of("US/Eastern"))); // ZonedDateTime
		// 输出：2018-04-15T02:40:34.253-04:00[US/Eastern]
		// 对比观察两种美国西部时间
		// 第一种方式直接输出当地时间。
		// // 第二种方式前边的部分是本地的，后面加上与UTC的时差信息，再加上时区信息。
	}

	// 演示时间戳的一些不恰当的使用方法
	public static void test02InstantPlus() {
		// 下面做一些实验
		Instant inst2 = Instant.ofEpochSecond(0); //
		System.out.println(inst2); // 1970-01-01T00:00:00Z
		System.out.println(inst2.ofEpochSecond(1)); // 1970-01-01T00:00:01Z <--

		Instant inst3 = Instant.ofEpochSecond(1); // +1s
		System.out.println(inst3); // 1970-01-01T00:00:01Z
		System.out.println(inst3.ofEpochSecond(1)); // 1970-01-01T00:00:01Z <--
		System.out.println(inst3.ofEpochSecond(-1)); // 1969-12-31T23:59:59Z <--
		System.out.println(inst3.ofEpochSecond(-2)); // 1969-12-31T23:59:58Z <--

		Instant inst4 = Instant.ofEpochSecond(1000); // +1000s
		System.out.println(inst4); // 1970-01-01T00:16:40Z
		System.out.println(inst4.ofEpochSecond(1)); // 1970-01-01T00:00:01Z <--

		// 标注的位置是不是和想象中的不太一样！注意到“神奇之处”都是有警告的。
		// 结论：遵循静态方法使用类调用而不是实例调用。
		// 如果从1970-01-01T00:00:00.000开始算，那就Instant.ofEpochXXXX调用一次就好。

	}
}
