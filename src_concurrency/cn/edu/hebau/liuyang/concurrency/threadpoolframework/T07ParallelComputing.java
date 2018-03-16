package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池的应用——并行计算<br>
 * 需求：从1到200,000的素数<br> 
 * 思路：计算切片<br>
 * 
 * @author liuyang
 *
 */
public class T07ParallelComputing {

	private static final int CPU_CORE_NUM = 4;
	private static final int CALC_RANGE = 200000;

	public static void main(String[] args) throws Exception {
		//calcSingleThread();// 单线程 2015ms
		calcMultiThread();// 多线程 729ms
	}

	// 单线程计算
	static void calcSingleThread() {
		long begin = System.currentTimeMillis();

		List<Integer> results = getPrime(1, CALC_RANGE);
		
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - begin) + "ms");
	}

	// 多线程计算（使用线程池，并行计算）
	static void calcMultiThread() throws InterruptedException, ExecutionException {
		//long begin = System.currentTimeMillis();
		ExecutorService service = Executors.newFixedThreadPool(CPU_CORE_NUM);

		// 切片(看着切片不公平？筛法求素数，数值越大计算越大需要的时间越长。)
		MyTask t1 = new MyTask(1, 80000);
		MyTask t2 = new MyTask(80001, 130000);
		MyTask t3 = new MyTask(130001, 170000);
		MyTask t4 = new MyTask(170001, 200000);

		// 放入线程池
		Future<List<Integer>> f1 = service.submit(t1);
		Future<List<Integer>> f2 = service.submit(t2);
		Future<List<Integer>> f3 = service.submit(t3);
		Future<List<Integer>> f4 = service.submit(t4);

		long begin = System.currentTimeMillis();
		
		f1.get();
		f2.get();
		f3.get();
		f4.get();
		service.shutdown();
		
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - begin) + "ms");
		
	}

	static class MyTask implements Callable<List<Integer>> {
		int startPos = 0, endPos = 0;

		MyTask(int startPos, int endPos) {
			this.startPos = startPos;
			this.endPos = endPos;
		}

		@Override
		public List<Integer> call() throws Exception {
			List<Integer> result = getPrime(startPos, endPos);
			return result;
		}
	}

	// 算质数
	private static List<Integer> getPrime(int startPos, int endPos) {
		List<Integer> results = new ArrayList<>();
		for (int i = startPos; i <= endPos; i++) {
			if (isPrime(i)) {
				results.add(i);
			}
		}
		System.out.println(results);
		return results;
	}

	// 判断质数Core
	static boolean isPrime(int num) {
		boolean flag = true;
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
