package cn.edu.hebau.liuyang.questions;

/**
 * 全排列 <br>
 * 参考：
 * 1. 耿宇航<br>
 * 2. 天大辅导班笔记<br>
 * 
 * @author liuyang
 * @since 2017/11/28
 * 
 */
public class FullPermutation {

	private static final String[] arr1 = { "A", "B", "C", "D" };// 待排数组
	private static final Integer[] arr2 = { 1, 2, 3, 4 };// 待排数组2

	//
	/**
	 * 泛型方法示例(可以在没有泛型定义的类中使用)<br>
	 * 参数类型由传入参数决定
	 * 
	 * @param t
	 * @return
	 */
	public static <T> T fun(T t) {
		// ...
		return t;
	}

	/**
	 * 给出指定数组内容的全排列（从数组开始排列到第k个位置）
	 * 
	 * @param v
	 *            待处理数组
	 * @param k
	 *            待处理元素总个数(前k个元素，如果全排就输入v的大小)
	 */
	public static <T> void fullPermutation(T[] v, int k) {
		// 参数判断
		if (null == v || v.length == 0) {
			return;
		}
		if (k < 1 || k > v.length) {
			return;
		}

		// 递归处理
		if (k > 1) {// 待排元素个数 k > 1 时
			T temp = null;
			for (int i = 0; i < k; ++i) {
				// 1. 先交换 v[i] <-> v[k-1]
				temp = v[k - 1];
				v[k - 1] = v[i];
				v[i] = temp;

				// 2. 缩小范围
				fullPermutation(v, k - 1);

				// 3. 交换回来 v[i] <-> v[k-1]（回溯）代码与前面的交换相同。
				temp = v[k - 1];
				v[k - 1] = v[i];
				v[i] = temp;
			}
		} else {// 待排元素个数 k = 1 时 
			// 这时，只有唯一一种情况，那么就输出整个数组的内容。
			for (int i = 0; i < v.length ; ++i) {
				System.out.print(v[i] + "\t");
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		
		fullPermutation(arr1, arr1.length);
		//fullPermutation(arr2, arr2.length);
		
		//fullPermutation(arr1, arr1.length - 2);// 只排列 第[1, arr1.length-2]个数
		fullPermutation(arr2, arr2.length - 2); // 只排列 第[1, arr2.length-2]个数
	}
}
