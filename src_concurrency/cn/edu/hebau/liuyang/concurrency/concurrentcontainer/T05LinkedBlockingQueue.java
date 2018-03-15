package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 无界阻塞队列
 * 使用LinkedBlockingQueue的生产者-消费者
 * put()       BlockingQueue上，如果满了就会等待
 * take()      BlockingQueue上，如果空了就会等待
 * @author liuyang
 *
 */
public class T05LinkedBlockingQueue {

	static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

	static Random r = new Random();

	public static void main(String[] args) {
		// 生产者
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				try {
					strs.put("a" + i);
					TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "producer").start();

		// 消费者
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (;;) {
					try {
						System.out.println(Thread.currentThread().getName() + " take - " + strs.take());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "consumer" + i).start();
		}
	}

}
