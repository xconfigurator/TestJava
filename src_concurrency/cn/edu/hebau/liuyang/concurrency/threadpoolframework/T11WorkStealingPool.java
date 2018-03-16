package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 可以理解为每个线程都维护自己的任务队列。<br>
 * 当某个线程将自己的队列任务完成后会抢（偷）其他线程队列中的任务。<br>
 * 使用ForkJoinPool实现。<br>
 * 
 * @author liuyang
 *
 */
public class T11WorkStealingPool {
	public static void main(String[] args) throws IOException {
		ExecutorService service = Executors.newWorkStealingPool();
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		// 根据CPU是几核心（看来超线程也计算在内）就默认起几个线程。台式机是4物理核心8线程机器。
		service.execute(new R(1000));// core1
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));
		service.execute(new R(2000));// core已沾满
		service.execute(new R(2000));// 搞九个任务。这个任务咋办？答：第一个线程执行完之后回来运行这个任务！因为第一个任务只执行1s，所以一定由它来接管这个任务。
		
		
		// 由于产生的是精灵线程（守护线程、后台线程）， 主线程不阻塞的话看不到输出。
		// 主线程退出之后，其实守护线程还在运行。
		// 如何观察守护线程？答：打断点。
		// 阻塞方法1
		//System.in.read();
		// 阻塞方法2
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static class R implements Runnable {
		
		int time;
		
		R(int t) {
			this.time = t;
		}
		
		@Override
		public void run() {
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(time + " " + Thread.currentThread().getName());
		}
	}
}
