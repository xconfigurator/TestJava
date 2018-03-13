package cn.edu.hebau.liuyang.concurrency.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock<br>
 * 下面演示synchronized完成不了的功能——尝试锁定<br>
 * 即：能锁就锁，不能锁就退出。<br>
 * 而synchronized是：如果得不到锁就一直等。<br>
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
public class ReentrantLock2 {

	Lock lock = new ReentrantLock();

	void m1() {
		lock.lock();
		try {
			for (int i = 0; i < 10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 使用tryLock进行尝试锁，不管锁定与否，方法都将继续执行
	 * 可以根据tryLock的返回值来判断是否锁定
	 * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中。
	 */
	void m2() {
		// 方式1
//		boolean tryLockResult = lock.tryLock();
//		System.out.println("m2 ... " + tryLockResult);
//		if (tryLockResult) {
//			lock.unlock();
//		}
		
		// 方式2 指定时间
		boolean tryLockResult = false;
		try {
			tryLockResult = lock.tryLock(5, TimeUnit.SECONDS);
			System.out.println("m2 ... " + tryLockResult);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (tryLockResult) {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock2 r = new ReentrantLock2();
		new Thread(r::m1).start();
		TimeUnit.SECONDS.sleep(1);
		new Thread(r::m2).start();
	}

}
