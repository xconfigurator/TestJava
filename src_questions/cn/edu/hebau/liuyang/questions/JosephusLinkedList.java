package cn.edu.hebau.liuyang.questions;

import java.util.List;

/**
 * 约瑟夫问题-链表版本<br>
 * 参考：<br>
 * 1.《数据结构（用面向对象方法和C++语言描述）》 第二版 殷人昆 <br>
 * 2.马士兵Java教学<br>
 * 
 * @author liuyang
 * @since 2017/11/28
 */
public class JosephusLinkedList {

	
	/**
	 * 用整数序列{1， 2， 3， ..., n}表示围坐在圆桌周围的人<br>
	 * 并使用测试数据来测试程序健壮性和正确性<br>
	 * 第一组：n = 9, s = 1, m = 5<br>
	 * 第二组：n = 9, s = 1, m = 0<br>
	 * 第三组：n = 9, s = 1, m = 10<br>
	 */
	private static final int MAX_NUM_N = 9;		// 参与人数
	private static final int START_INDEX_S = 1;	// 起始报数位置
	private static final int STEP_M = 10;		// 第几个人退出
	
	
	/**
	 * 输出列表内容
	 * @param l
	 */
	private static void printList(List<Integer> l) {
		for (Integer i : l) {
			System.out.print(i + "\t");
		}
		System.out.println("");
	}
	
	
	public static void main(String[] args) {
		// 约瑟夫问题的循环链表解法演示
	}

}
