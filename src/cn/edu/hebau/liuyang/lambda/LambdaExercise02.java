package cn.edu.hebau.liuyang.lambda;

import static java.lang.System.out;

/**
 * 练习2： 自定义函数式接口
 * 
 * 要求：
 * 操作1，将一个字符串转换成大写，并作为方法的返回值。
 * 操作2：将一个字符串的第2个和第4个索引位置进行截取子串。
 * 
 * @author liuyang
 *
 */
public class LambdaExercise02 {

	public static void main(String[] args) {
		out.println(strHandler("hello, world", (s) -> s.toUpperCase()));
		out.println(strHandler("hello, world", (s) -> s.substring(2, 5)));
	}
	
	public static String strHandler(String str, LambdaExercise02IFunction func) {
		return func.process(str);
	}

}

