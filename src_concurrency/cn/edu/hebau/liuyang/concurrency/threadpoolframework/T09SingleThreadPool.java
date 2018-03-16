package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 保证任务前后一定是顺序执行。
 * @author liuyang
 *
 */
public class T09SingleThreadPool {

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		for (int i = 0; i< 5; i++) {
			final int j = i;
			service.execute(()->{
				System.out.println(j + " " + Thread.currentThread().getName());
			});
		}
		service.shutdown();
	}
}
