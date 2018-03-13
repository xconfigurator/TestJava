package cn.edu.hebau.liuyang.concurrency.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock<br>
 * 可以替代synchronized<br>
 * 下面演示ReentrantLock完成与synchronized同样的功能。<br>
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
public class ReentrantLock1 {
	
	Lock lock = new ReentrantLock();
	
	void m1() {
		lock.lock();// 锁定
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println("m1 ... " + i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();// finally中手动释放锁
		}
	}
	
	void m2() {
		lock.lock();	// 锁定
		System.out.println("m2 ...");
		lock.unlock();	// 手动释放锁
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock1 r = new ReentrantLock1();
		new Thread(r::m1).start();
		TimeUnit.SECONDS.sleep(1);
		new Thread(r::m2).start();
	}

}
