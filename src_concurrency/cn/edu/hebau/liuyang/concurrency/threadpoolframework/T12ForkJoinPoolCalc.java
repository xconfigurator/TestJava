package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 从1累加到1000000对比 —— ForkJoinPool
 * @author liuyang
 *
 */
public class T12ForkJoinPoolCalc {

	private static final long AIM_NUM = 100000000;	// 总累加数量
	private static final long TASK_SCALE = 1000;	// 任务规模，每个线程只计算1000个
	
	public static void main(String[] args) throws IOException {
		ForkJoinPool fjp = new ForkJoinPool();	// 整个递归过程由ForkJoinPool来维护。
		MyRecursiveTask task = new MyRecursiveTask(1, AIM_NUM);
		fjp.execute(task);
		
		// 开始计算方法1
		//long result = task.join();
		//System.out.println(result);
		//后台启动守护进程进行计算....
		//想要看到输出就需要阻塞主线程。
		//System.in.read();
		
		// 开始计算方法2
		/**
		 * fjp.submit(task);	// 提交没有返回值的任务（过程）
		 * fjp.invoke(task);	// 调用有返回值的任务（函数）
		 */
		long result = fjp.invoke(task);
		System.out.println(result);
		fjp.shutdown();
	}
	
	// 继承RecursiveTask
	static class MyRecursiveTask extends RecursiveTask<Long> {
		private long start;
		private long end;
		
		MyRecursiveTask(long start, long end) {	// 约定是闭区间[]
			this.start = start;
			this.end = end;
		}
		
		// 有返回值
		@Override
		protected Long compute() {
			if (end - start <= TASK_SCALE) {	//	计算分片
				long sum = 0L;
				for (long i = start; i <= end; i++) {// [] 注意i<=
					sum += i;
				}
				return sum;
			} else {							// 分解任务(下面的写法比较固定)
				long middle = start + (end - start) / 2;
				MyRecursiveTask subTask1 = new MyRecursiveTask(start, middle);
				MyRecursiveTask subTask2 = new MyRecursiveTask(middle + 1, end);// [] 注意是middle + 1
				subTask1.fork();// 子线程1启动
				subTask2.fork();// 子线程2启动	
				return subTask1.join() + subTask2.join();
			}
		}
	}
	
}
