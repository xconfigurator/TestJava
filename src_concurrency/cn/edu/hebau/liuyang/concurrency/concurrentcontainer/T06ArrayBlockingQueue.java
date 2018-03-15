package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有界阻塞队列
 * 使用ArrayBlockingQueue的生产者-消费者
 * put()       BlockingQueue上，如果满了就会等待
 * take()      BlockingQueue上，如果空了就会等待
 * @author liuyang
 *
 */
public class T06ArrayBlockingQueue {

	static int CACHE_SIZE = 10;					// 缓冲区大小
	static int PRODUCER_NUM = 2;				// 生产者个数
	static int CONSUMER_NUM = 10;				// 消费者个数
	static int PROD_NUM_PER_PRODUCER = 50;		// 每个生产者生产的产品个数
	
	static BlockingQueue<String> strs;			// 阻塞队列 
	
	static Random r = new Random();

	static {		
		//strs = new LinkedBlockingQueue<>();
		strs = new ArrayBlockingQueue<String>(CACHE_SIZE);
	}
	
	public static void main(String[] args) {
		// 生产者
		for (int i = 0; i < PRODUCER_NUM; i++) {
			new Thread(() -> {
				for (int j = 0; j < PROD_NUM_PER_PRODUCER; j++) {
					try {
						strs.put("a" + j);
						TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}, "producer" + i).start();
		}

		// 消费者
		for (int i = 0; i < CONSUMER_NUM; i++) {
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
