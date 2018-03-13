package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o，如果o的属性发生改变，不影响锁的使用。<br>
 * 但是如果o指向了另外的对象，则会出现问题。<br>
 * 应该避免将锁定对象的引用变成另外的对象。
 * @author liuyang
 *
 */
public class T011 {
	
	Object o = new Object();
	
	void m() {
		synchronized(o) {
			while(true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}

	public static void main(String[] args) {
		T011 t = new T011();
		// 启动第一个线程
		new Thread(t::m, "t1").start();
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 创建第二个线程
		new Thread(t::m, "t2").start();
		
		// 更换锁定对象
		// 锁都换了，所以t2得以执行，如果注释掉这句话，线程t2将永远得不到执行。
		// 证明：Java的锁是实现在堆中对象上的，而不是在栈上的。
		t.o = new Object();
		
	}

}
