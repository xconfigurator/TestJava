package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02CopyOnWriteList {

	public static void main(String[] args) {
		// 一堆List
		List<String> list;
		// list = new ArrayList<>();// 并发会有问题。
		// list = Collections.synchronizedList(new ArrayList<>());// 60ms 60ms 70ms
		// list = new Vector<>();// 70ms 68ms 70ms 64ms
		list = new CopyOnWriteArrayList<>();// 3491ms 3520ms //适合写得很少，读得很多的场景

		test(list);
	}

	// 测试用例
	public static void test(List<String> list) {
		Random r = new Random();
		Thread[] threads = new Thread[100];

		for (int i = 0; i < threads.length; i++) {
			Runnable task = new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 1000; i++) {
						// map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
						list.add("a" + r.nextInt(10000));
					}
				}
			};
			threads[i] = new Thread(task);
		}

		runAndComputeTime(threads);

		System.out.println(list.size());
	}

	// 运行并计时
	public static void runAndComputeTime(Thread[] threads) {
		long begin = System.currentTimeMillis();

		Arrays.asList(threads).forEach(t -> t.start());
		Arrays.asList(threads).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - begin) + "ms");
	}
}
