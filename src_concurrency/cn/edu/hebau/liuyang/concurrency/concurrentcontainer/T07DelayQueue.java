package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue<br>
 * DelayQueue也是一种阻塞Queue,且无界。<br>
 * 队列中的每一个任务只有等待了一段时间之后才可以被取出。<br>
 * 
 * @author liuyang
 *
 */
public class T07DelayQueue {

	static BlockingQueue<MyTask> tasks = new DelayQueue<>();

	static Random r = new Random();

	static class MyTask implements Delayed {

		long runningTime;

		MyTask(long rt) {
			this.runningTime = rt;
		}

		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			} else {
				return 0;
			}
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String toString() {
			return String.valueOf(runningTime);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000);// 1秒后执行
		MyTask t2 = new MyTask(now + 2000);// 2秒后执行
		MyTask t3 = new MyTask(now + 1500);
		MyTask t4 = new MyTask(now + 2500);
		MyTask t5 = new MyTask(now + 500);

		tasks.put(t1);
		tasks.put(t2);
		tasks.put(t3);
		tasks.put(t4);
		tasks.put(t5);

		System.out.println(tasks);

		for (int i = 0; i < 5; i++) {
			System.out.println(tasks.take());
		}

	}

}
