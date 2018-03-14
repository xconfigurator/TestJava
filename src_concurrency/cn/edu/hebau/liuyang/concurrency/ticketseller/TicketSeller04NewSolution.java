package cn.edu.hebau.liuyang.concurrency.ticketseller;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 有N张火车票，每张火车票都有一个编号，同时有10个窗口对外售票。请写程序模拟。<br>
 * 
 * 问题引出<br>
 * 从问题暴露，逐步引入同步容器。<br>
 * 
 * 另外一种解决方法：并发容器<br>
 * 
 * 
 * @author liuyang
 *
 */
public class TicketSeller04NewSolution {

	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	
	static {
		for (int i = 0; i < 10000; i++) {// 10000张票
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {// 10个售票窗口
			new Thread(() -> {
				String s = null;
				while((s = tickets.poll()) != null) {// ConcurrentLinkedQueue的poll底层是CAS实现
					System.out.println("销售了 -- " + s);
				}
			}).start();
		}
	}

}
