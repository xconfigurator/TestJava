package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 开始的时候一个线程都没有，来一个任务，就启动一个线程。<br>
 * 如果来了第三个任务，恰好线程池中第一个或第二个任务已经执行完毕，那就正好使用空闲线程执行第三个任务。<br>
 * 如果这时前两个线程都还没有完成任务则启动新的线程执行任务。<br>
 * 线程空闲超过60s（默认），则结束。<br>
 * @author liuyang
 *
 */
public class T08CachedPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(service);// 这时线程池里一个线程都没有
		
		for (int i = 0; i < 2; i++) {
			service.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);	// 线程池中的两个线程正在运行……
		TimeUnit.SECONDS.sleep(80);		// 超过默认的线程生存时间（60s）
		System.out.println(service);	// 超过最大存货周期，线程池清空
		
		service.shutdown();
	}

}
