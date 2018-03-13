package cn.edu.hebau.liuyang.concurrency.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 要求：<br>
 * 实现一个容器，提供两个方法，add和size<br>
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束。<br>
 * 
 * 改进：CountDownLatch<br>
 * 分析：MyContainer2中，比较高效地实现了要求，但wait和notify配合略显复杂。<br>
 * 说明：使用Latch（门闩）替代wait/notify来进行通知，好处是通信方式简单，同时也可以指定等待时间<br>
 * CountDownLatch不涉及锁定，当count的值为0时当前线程继续运行。<br>
 * 当不涉及同步，只涉及线程通信的时候，用synchronized + wait/notify就显得太重了<br>
 * 这时应该考虑CountDownLatch/CyclicBarrier/Semaphore
 * 注意：这种运行方法，必须保证t2先执行，也就是首先让t2监听才可以<br>
 * 
 * @author liuyang
 *
 */
public class MyContainer3 {
	
	volatile List lists = new ArrayList();
	
	public void add(Object o) {
		lists.add(o);
	}
	
	public int size() {
		return lists.size();
	}

	public static void main(String[] args) throws InterruptedException {
		MyContainer3 c = new MyContainer3();
		
		CountDownLatch latch = new CountDownLatch(1);
		
		// 线程t2
		new Thread(()->{
			System.out.println("t2启动");
			if (c.size() != 5) {
				try {
					latch.await();
					
					// 也可以指定等待时间
					//latch.await(5000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("t2结束");
		}, "t1").start();
		
		TimeUnit.SECONDS.sleep(1);
		
		// 线程t1
		new Thread(()->{
			System.out.println("t1启动");
			for (int i = 0; i < 10; i++) {
				c.add(new Object());
				System.out.println("add " + i);
				
				if(c.size() == 5) {
					// 打开门闩，让t2得以执行
					latch.countDown();
				}
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2").start();
	}

}
