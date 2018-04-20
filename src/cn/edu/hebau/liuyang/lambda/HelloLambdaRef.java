package cn.edu.hebau.liuyang.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用和构造器引用
 * 可以理解为Lambda表达式的另一种写法
 * 
 * @author liuyang
 *
 */
public class HelloLambdaRef {
	public static void main(String[] args) {
		System.out.println("######################################");
		methodRef();
		System.out.println("######################################");
		constructorRef();
		System.out.println("######################################");
		arrayRef();
	}
	
	// 方法引用
	public static void methodRef() {
		// 1. 实例方法, 对象
		Consumer<String> con = (x) -> System.out.println(x);

		PrintStream ps = System.out;
		Consumer<String> con1 = ps::println;

		Consumer<String> con2 = System.out::println;
		con2.accept("hello, world");

		// 2. 类：：静态方法名
		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;

		// 3. 类：：实例方法名
		// 比较两个字符串
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		BiPredicate<String, String> bp2 = String::equals;
	}

	// 构造器引用
	public static void constructorRef() {
		Supplier<Object> sup = () -> new Object();
		Supplier<Object> sup2 = Object::new;
		
		// 注意并不一定是调用无参构造器！
	}
	
	// 数组引用
	public static void arrayRef() {
		Function<Integer, String[]> fun = (x) -> new String[x];
		String[] strs = fun.apply(10);
		System.out.println(strs.length);
		
		Function<Integer, String[]> fun1 = String[]::new;
		String[] strs1 = fun1.apply(5);
		System.out.println(strs1.length);
	}
}
