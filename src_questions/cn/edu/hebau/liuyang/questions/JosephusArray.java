package cn.edu.hebau.liuyang.questions;

/**
 * 约瑟夫问题-数组版本<br>
 * 参考：<br>
 * 1.《数据结构（用面向对象方法和C++语言描述）》 第二版 殷人昆 <br>
 * 2.马士兵Java教学<br>
 * 
 * @author liuyang
 * @since 2017/11/28
 */
public class JosephusArray {

	/**
	 * 用整数序列{1， 2， 3， ..., n}表示围坐在圆桌周围的人<br>
	 * 并使用测试数据来测试程序健壮性和正确性<br>
	 * 第一组：n = 9, s = 1, m = 5<br>
	 * 第二组：n = 9, s = 1, m = 0<br>
	 * 第三组：n = 9, s = 1, m = 10<br>
	 */
	private static final int MAX_NUM_N = 9; // 参与人数
	private static final int START_INDEX_S = 1; // 起始报数位置
	private static final int STEP_M = 10; // 第几个人退出

	/**
	 * 数组实现版本
	 * 
	 * @param a
	 *            待处理数组
	 * @param n
	 *            参与人数 （其实在Java中这个参数可以不传， 通过a.length获得）
	 * @param s
	 *            从第s个人开始报数（注意整数序列是{1, 2, 3, ... , n}，即问题域下标从1开始）
	 * @param m
	 *            第m个人退出
	 */
	public static void josephusArray(int[] a, int n, int s, int m) {

		// 1. 参数检测
		if (m <= 0) {// 参数检测
			System.err.println("输入的M = " + m + "非法。");
			return;
		}

		// 2. 初始化,执行n次
		for (int i = 0; i < n; i++) {// {1, 2, 3, ... , n} 这里的i是数组的实际下标值
			a[i] = i + 1;
		}

		// 3. 报名起始位置
		int idx = (s - 1 + n) % n;// idx是数组的实际下标值。 s - 1是把逻辑值映射回数组物理值。

		// 4. 逐个出局，执行n-1次
		/**
		 * 这里的k对应逻辑上的循环次数，即第n - k + 1次循环。<br>
		 * 为啥这样写？<br>
		 * 目的1：控制循环n-1次。<br>
		 * 目的2：倒写是为了实现类似向上冒大泡的效果（但注意下标处理不同）<br>
		 */
		for (int k = n; k > 1; --k) {
			// 4.1. 调整开始报数的位置
			/**
			 * 考虑第1次执行的情况，k == n<br>
			 * 下面的判断，当idx == n时说明数组下标idx已经跑到了最后一个元素的下一个元素的位置。<br>
			 * 即k值实际上是比数组实际下标值大1的。<br>
			 * 问题来了，为啥不处理一下下标，干嘛还要差1个<br>
			 */
			if (idx == k) {
				idx = 0;
			}

			// 4.2. 计算出局的位置
			idx = (idx + m - 1) % k;

			// 4.3. 将出局者移动到第k-1个位置
			/**
			 * 当然，如果出局的就是第k-1个位置的人就没有必要再交换
			 */
			int temp = 0;
			if (idx != k - 1) {
				temp = a[idx];// 出局的那个人
				for (int j = idx; j < k - 1; ++j) {
					a[j] = a[j + 1];
				}
				a[k - 1] = temp;
			}
		}

		// 5.数组内容全部逆置，得到出局序列
		int temp = 0;
		for (int i = 0; i < n / 2; ++i) {
			temp = a[i];
			a[i] = a[n - 1 - i];
			a[n - 1 - i] = temp;
		}

		/**
		 * 【小结】<br>
		 * 1. 这个算法是在空间复杂度有要求的情况下。做编程练习非常合适。<br>
		 * 2. 另解<br>
		 * 但如果对空间复杂度要求没多么严格，记录出局序列的任务完全可以再开一个队列。<br>
		 * 挑选出一个出局者之后就把他从队尾加入队列,输出时从队头顺序输出即可获得出局顺序。<br>
		 * 而且用队列的解法应该是O(n)级别的。空间代价也不过是使用了与原数组等长的队列。<br>
		 */
	}

	/**
	 * 输出数组内容
	 * 
	 * @param a
	 */
	private static void printArr(int[] a) {
		for (int i = 0; i < a.length; ++i) {
			System.out.print(a[i] + "\t");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		// 约瑟夫问题数组解法演示
		int[] arr = new int[MAX_NUM_N];
		josephusArray(arr, MAX_NUM_N, START_INDEX_S, STEP_M);
		System.out.println("# n = " + MAX_NUM_N + " s= " + START_INDEX_S + " m = " + STEP_M + "时，出局的序列为：");
		printArr(arr);
	}

}
