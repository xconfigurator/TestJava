package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * volatile关键字，使一个变量在多个线程间可见。<br>
 * A B线程都用到一个变量，Java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道。<br>
 * 使用volatile关键字，会让所有线程都读到变量修改的值。<br>
 * 
 * 下面的代码中，running位于堆中的t对象内。<br>
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程工作区，在运行过程中直接使用这个copy,并不会每次都区读堆内存。<br>
 * 这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行<br>
 * 
 * 使用volatile，将会强制所有线程都去堆中读取running的值。<br>
 * 
 * 参考文章：<br>
 * http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * 
 * 结论：<br>
 * volatile并不能保证多个线程共同修改running变量时锁带来的不一致性<br>
 * 也就是说volatile不能替代synchronized<br>
 * 
 * @author liuyang
 *
 */
public class T007Volatile {
	
	// 这个是Java“挑战”操作系统原理中“线程天然共享内存”的说法。其实也不算挑战，应该就是JVM具体实现方法（JMM：Java Memory Model）所致。
	// 增加volatile，在线程间共享该变量，与操作系统原理中描述的一致。
	volatile boolean running = true;
	// 如果不加volatile，则Java会为每个线程维护一份拷贝。
	//boolean running = true;
	
	void m() {
		System.out.println("m start");
		while (running) {
			// 实测，如果增加下面这段睡眠代码，在不加volatile情况下也能停止。
			// 解释: running会被放入CPU的Cache。如果不加这段休眠代码，Cache无法刷新，造成堆中数据修改，但t1无法感知。
			// 加了这段代码后，Cache刷新了，实际上也就达到了
			// 而增加volatile的机制是，一旦堆中数据发生改变，会通知其他线程，强制更新相关缓存。
			/*
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			*/
		};
		System.out.println("m end");
	}

	public static void main(String[] args) throws InterruptedException {
		T007Volatile t = new T007Volatile();
		new Thread(t::m, "t1").start();
		
		TimeUnit.SECONDS.sleep(1);
		
		// 在main线程中将堆中t对象里的running设置为false。
		t.running = false;
		// 本意是想停止线程运行的。但在不加volatile的情况下无效。加了才会达到预期。
		// 实测
	}

}
