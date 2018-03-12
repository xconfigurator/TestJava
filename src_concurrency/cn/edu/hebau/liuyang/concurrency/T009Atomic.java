package cn.edu.hebau.liuyang.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 应对++ --这种场景，Java提供了AtomicXXX类<br>
 * 类似于T008Volatile的测试逻辑，即搞10个线程去执行累加<br>
 * 建议：简单场景,能用Atomic解决的就用Atomic类解决。
 * @author liuyang
 *
 */
public class T009Atomic {

	AtomicInteger count = new AtomicInteger(0);

	void m() {// 这时不需要使用synchronized关键字. 10000 * 10
		for (int i = 0; i < 10000; i++) {// 加10000次
			count.incrementAndGet();// count ++ 不具备原子性,而AtomicXXX类的相关方法保证操作不被打断（联想NUDT讲的硬件“读后置1”）。
		}
	}

	public static void main(String[] args) {
		T009Atomic t = new T009Atomic();

		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}

		threads.forEach((o) -> o.start());
		threads.forEach((o) -> {
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
