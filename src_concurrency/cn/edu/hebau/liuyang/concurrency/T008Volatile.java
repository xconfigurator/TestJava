package cn.edu.hebau.liuyang.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile是volatile，synchronized是synchronized，谁都不能替代谁。
 * @author liuyang
 *
 */
public class T008Volatile {
	
	/**
	 * int count = 0;<br>
	 * void m() {}<br>
	 * 实测值：28000左右
	 */
	
	/**
	 * volatile int count = 0;<br>
	 * void m() {}<br>
	 * 实测值：32138左右
	 */
	
	/**
	 * int count = 0;或volatile int count = 0;<br>
	 * synchronized void m() {}
	 * 实测值：100000
	 */
	
	volatile int count = 0;// volatile只保证可见性，而与synchronized保证的加锁不是一回事。
	//int count = 0;// 不加volatile，仅使用synchronized也能达到预期。
	
	//void m() {// 每次都不一样。这玩意也看心情。
	synchronized void m( ) {// 10000 * 10
		for (int i = 0; i < 10000; i++) {// 加10000次
			count ++;
		}
	}

	public static void main(String[] args) {
		T008Volatile t = new T008Volatile();
		
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}
		
		threads.forEach((o)->o.start());
		threads.forEach((o)-> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		// 看看这10个线程折腾完后的结果
		System.out.println(t.count);
	}

}
