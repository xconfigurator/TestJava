package cn.edu.hebau.liuyang.lambda;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * 
 * @author liuyang
 *
 */
public class Hello {
	public static void main(String[] args) {
		// 引入Lambda之前
		TreeSet<Integer> ts1 = new TreeSet<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
			
		});
		
		// 引入Lambda之后
		TreeSet<Integer> ts2 = new TreeSet<>((x, y) -> Integer.compare(x, y)) ;
	}
}
