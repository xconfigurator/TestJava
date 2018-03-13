package cn.edu.hebau.liuyang.concurrency.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock<br>
 * 公平锁<br>
 * 默认情况下是非公平锁，synchronized加的也是非公平锁。<br>
 * 
 * 在使用synchronized的情况下由于m1锁定this，只有m1执行完毕的时候m2才能执行。<br>
 * 
 * 使用ReentrantLock可以完成同样的功能<br>
 * 需要注意的是，必须要！必须要！必须要！手动释放锁！（注意与synchronized的区别）<br>
 * 使用synchronized锁定，如果遇到异常，则JVM会释放锁<br>
 * 但如过使用java.util.concurrent.locks.Lock锁定，则必须手动释放，通常的做法是在finally中释放锁<br>
 * 
 * 
 * @author liuyang
 *
 */
public class ReentrantLock4 extends Thread{
	
	private static ReentrantLock lock = new ReentrantLock(true);// 参数为true表示为公平锁，注意对比输出结果。
	//private static ReentrantLock lock = new ReentrantLock();// 参数为true表示为公平锁，注意对比输出结果。
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			lock.lock();// 在非公平锁的情况下下次申请到锁的人有可能还是它，但如果是公平锁，则会调度给其他线程获得锁从而得到执行机会。
			System.out.println(Thread.currentThread().getName() + "获得锁");
			lock.unlock();
		}
	}	
	
	public static void main(String[] args) {
		ReentrantLock4 r = new ReentrantLock4();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}

}
