package cn.edu.hebau.liuyang.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

public class HelloLambdaExamples {
	public static void main(String[] args) {
		// 1. 无参数，无返回值
		new Thread(() -> System.out.println("hello Lambda")).start();

		// 2. 一个参数，无返回值
		Consumer<String> consumer1 = (s) -> System.out.println(s); // 推荐写法。
		consumer1.accept("hello, world");

		Consumer<String> consumer2 = (s) -> {
			System.out.println(s);
		}; // 完整写法。
		consumer2.accept("hello, world");

		Consumer<String> consumer3 = s -> System.out.println(s); // 省略写法。若参数和语句都只有一条，则() {}可省略

		// 3. 两个参数，有返回值
		Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);// 推荐写法。如果方法体只有一条语句，可以省略return。

		Comparator<Integer> comparator2 = (x, y) -> {
			return Integer.compare(x, y);
		}; // 完整写法

		Comparator<Integer> comparator3 = (Integer x, Integer y) -> Integer.compare(x, y);// 关于参数，如果手动标注类型，那么就需要明确标注所有参数类型。
	}
}
