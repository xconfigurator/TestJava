package cn.edu.hebau.liuyang.lambda;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置四大函数式接口示例
 * 方便使用Lambda表达式，不用每次使用都创建接口，即JDK提供了最常用的集中情况。
 * @author liuyang
 *
 */
public class HelloLambdaBuildInFuncInterface {

	public static void main(String[] args) {
		// java.util.function.Consumer	
		testConsumer(10000, (m) -> System.out.println("嘿嘿嘿：" + m));
		
		// java.util.function.Suppiler
		Random r = new Random();// JDK 8开始，如果在匿名内部类中使用了外部的对象，则自动添加final
		testSupplier(10, () -> r.nextDouble()).forEach(System.out::println);;		// 10个随机数  
		testSupplier(10000, () -> r.nextGaussian());								// 10000个服从标准正态分布的数据
		
		// java.util.function.Function
		int i = testFunction("hello, world", s -> s.length());
		System.out.println(i);
		
		// java.util.function.Predicate
		Set<String> set = ZoneId.getAvailableZoneIds();
		testPredicate(new ArrayList<String>(set), (str)->{
			return str.contains("Asia");	// 在这里制定规则，比如:只看亚洲时区。
		}).forEach(System.out::println);
	}
	
	// java.util.function.Consumer
	// 规则：花掉money，方法随意。
	public static void testConsumer(double money, Consumer<Double> func) {
		func.accept(money);
	}
	
	// java.util.function.Suppiler
	// 规则：产生指定个数的双精度实验数据（定制：可以是随机的、随机正态的）并放入集合中
	public static List<Double> testSupplier(int n, Supplier<Double> func) {
		List<Double> list = new ArrayList<>();
		for (int i=0; i < n; ++i) {
			list.add(func.get());
		}
		return list;
	}
	
	// java.util.function.Function
	// 规则：传入字符串，返回数字（定制：可以是字符串长度，可以是ASCII码值等等）
	public static Integer testFunction(String str, Function<String, Integer> func) {
		return func.apply(str);
	}
	
	// java.util.function.Predicate
	// 规则：满足某种条件（定制）的字符串放入集合中
	public static List<String> testPredicate(List<String> list, Predicate<String> func) {
		List<String> resultList = new ArrayList<String>();
		
		for (String str : list) {
			if (func.test(str)) resultList.add(str);
		}
		
		return resultList;
	}
}
