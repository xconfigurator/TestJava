package cn.edu.hebau.liuyang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 二分查找，使用JCF
 * @author liuyang
 * @since 2017/9/10
 */
public class HelloJCFBinarySearch {

	private static final String FILE_PATH = "F:/temp/test.txt";
	private static final int SEARCH_AIM = 4;// 44

	public static void main(String[] args) throws IOException {
		List<Integer> list = new ArrayList<Integer>();

		// 获取数据
		BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
		String line = null;
		while ((line = reader.readLine()) != null) {
			list.add(Integer.parseInt(line.trim()));
		}
		reader.close();

		// 排序
		list.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});

		// 二分查找
		int idx = Collections.binarySearch(list, SEARCH_AIM);
		System.out.println("#待搜索集合：" + list);
		System.out.println("#查找目标:" + SEARCH_AIM + "在排序后的集合位置是（返回负数表示没找到）：" + idx);
	}

}
