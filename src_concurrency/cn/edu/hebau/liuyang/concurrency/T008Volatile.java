package cn.edu.hebau.liuyang.concurrency;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile是volatile，synchronized是synchronized，谁都不能替代谁。
 * @author liuyang
 *
 */
public class T008Volatile {
	
	volatile int count = 0;
	
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
