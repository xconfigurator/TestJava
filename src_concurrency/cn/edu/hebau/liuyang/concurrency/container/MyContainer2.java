package cn.edu.hebau.liuyang.concurrency.container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 要求：<br>
 * 实现一个容器，提供两个方法，add和size<br>
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5时，线程2给出提示并结束。<br>
 * 
 * 改进：wait，notify。<br>
 * 分析：MyContainer1中，通过添加volatile虽然能够完成要求，但t2线程中的死循环很浪费CPU，如果不用死循环怎么做？<br>
 * 注意：<br>
 * 1. wait会释放锁，而notify不会释放。wait和notify都是通过对同一对象上的锁进行操作的。<br>
 * 2. 这种运行方法，必须保证t2先执行，也就是首先让t2监听才可以<br>
 * 3. notify之后t1必须释放锁，t2退出后也必须nofity通知t1，t1才能继续执行<br>
 * 
 * @author liuyang
 *
 */
public class MyContainer2 {

	volatile List lists = new ArrayList();
	final static Object lock = new Object();
	
	public void add(Object o) {
		lists.add(o);
	}
	
	public int size() {
		return lists.size();
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyContainer2 c = new MyContainer2();
		
		// 线程t2监控 （改进）而且必须先执行
		new Thread(()->{
			System.out.println("t2启动");
			synchronized(lock) {
				if (c.size() != 5) {
					try {
						lock.wait();//释放锁
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// t2结束后需要通知t1继续执行
				lock.notify();
			}
			System.out.println("t2结束");
			// lock.notify(); // 不可以在这里，否则抛出java.lang.IllegalMonitorStateException
		}, "t2").start();
		
		
		TimeUnit.SECONDS.sleep(1);
		
		// 线程t1添加
		new Thread(()->{
			System.out.println("t1启动");
			synchronized(lock) {
				for (int i = 0; i < 10; i++) {
					c.add(new Object());
					System.out.println("add " + i);
				
					if (c.size() == 5) {
						// notify不会释放锁，所以即便在这里通知了t2，t2因为得不到锁也不会运行。
						lock.notify();
						
						// 释放锁，以便让t2运行
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "t1").start();
		
	}

}
