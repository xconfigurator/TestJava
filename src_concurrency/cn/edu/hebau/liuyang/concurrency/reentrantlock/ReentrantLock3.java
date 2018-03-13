package cn.edu.hebau.liuyang.concurrency.reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock<br>
 * 响应Interrupt<br>
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
public class ReentrantLock3 {

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		
		Thread t1 = new Thread(()->{
			lock.lock();
			try {
				System.out.println("t1 start");
				TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);// 睡死了
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("t1 interrupted!");
			} finally {
				lock.unlock();
			}
		});
		t1.start();
		
		Thread t2 = new Thread(()->{
			//lock.lock();// 这样做无法响应t2.interrupt(); t2还是在一直等待t1释放锁
			try {
				lock.lockInterruptibly();// 可以对interrupt()方法做出响应
				System.out.println("t2 start");
				TimeUnit.SECONDS.sleep(5);
				System.out.println("t2 end");
			} catch (InterruptedException e) {
				System.out.println("t2 interrupted!");
			} finally {
				lock.unlock();
			}
		}) ;
		t2.start();
		
		TimeUnit.SECONDS.sleep(1);
		t2.interrupt();// 打断t2的等待。
	}

}
