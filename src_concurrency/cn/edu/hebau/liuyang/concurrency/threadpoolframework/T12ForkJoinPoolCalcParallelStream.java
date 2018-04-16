package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.stream.LongStream;

/**
 * 从1累加到1000000对比 —— ForkJoinPool
 * @author liuyang
 *
 */
public class T12ForkJoinPoolCalcParallelStream {

	private static final long AIM_NUM = 100000000;	// 总累加数量
	//private static final long TASK_SCALE = 1000;	// 任务规模，每个线程只计算1000个
		
	public static void main(String[] args) {
		// 并行流写法1
		long result = LongStream.rangeClosed(1, AIM_NUM).parallel().sum();
		System.out.println(result);
				
		// 顺序流写法2
		long result2 = LongStream.rangeClosed(1, AIM_NUM).parallel().reduce(0, Long::sum);
		System.out.println(result2);	
	}
	
	// 附
	public static void seqStream() {
		// 顺序流写法1
		long result = LongStream.rangeClosed(1, AIM_NUM).sum();
		System.out.println(result);
		
		// 顺序流写法2
		long result2 = LongStream.rangeClosed(1, AIM_NUM).reduce(0, Long::sum);
		System.out.println(result2);	
	}

}
