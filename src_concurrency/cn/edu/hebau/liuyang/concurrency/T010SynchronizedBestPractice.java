package cn.edu.hebau.liuyang.concurrency;

import java.util.concurrent.TimeUnit;

/**
 * synchronized最佳实践<br>
 * synchronized所包含的语句越少越好。就是要准确识别Critical Region。<br>
 * 比较m1和m2
 * 
 * @author liuyang
 *
 */
public class T010SynchronizedBestPractice {

	int count = 0;

	synchronized void m1() {
		// do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 业务逻辑中只有下面的语句需要sync，这事不应该给整个方法上锁
		count++;
		// do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	void m2() {
		// do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// 业务逻辑中只有下面的语句需要sync，这事不应该给整个方法上锁
		// 采用细粒度缩，提高并发
		synchronized (this) {
			count++;
		}
		
		// do sth need not sync
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// 略
	}

}
