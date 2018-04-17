package cn.edu.hebau.liuyang.lambda;

import static java.lang.System.out;

/**
 * 练习3： 练习带泛型的函数式接口
 * 
 * 要求： 
 * 操作1：计算两个long型参数的和。 
 * 操作2：计算两个long型参数的乘积。
 * 
 * 分析： 声明一个带两个暗星的函数式接口，泛型类型为<T, R> T为参数，R为返回值。
 * 
 * @author liuyang
 *
 */
public class LambdaExercise03 {

	public static void main(String[] args) {
		out.println(op(4l, 4l, (l1, l2) -> l1 + l2));
		out.println(op(4l, 4l, (l1, l2) -> l1 * l2));
	}

	public static Long op(Long l1, Long l2, LambdaExercise03IFunction<Long, Long> func) {
		return func.process(l1, l2);
	}
}
