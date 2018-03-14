package cn.edu.hebau.liuyang.concurrency.ticketseller;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张火车票都有一个编号，同时有10个窗口对外售票。请写程序模拟。<br>
 * 
 * 问题引出<br>
 * 从问题暴露，逐步引入同步容器。<br>
 * 
 * ArrayList不行那就换成Vector试试<br>
 * 
 * 这个程序存在以下问题<br>
 * 1. 虽然在Vector中，remove方法是原子的，但判断size()和remove()是分离的，中间有可能会被打断。
 * 2. 这个测试出现异常的可能性要远远小于ArrayList。也就是说这个bug更隐蔽了。可以睡眠以放大问题。
 * 
 * @author liuyang
 *
 */
public class TicketSeller02Vector {

	static Vector<String> tickets = new Vector<>();
	
	static {
		for (int i = 0; i < 10000; i++) {// 10000张票
			tickets.add("票编号：" + i);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {// 10个售票窗口
			new Thread(() -> {
				while (tickets.size() > 0) {
					
					// 放大出问题的可能性 begin
					try {
						TimeUnit.MILLISECONDS.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 放大出问题的可能性 end
					
					System.out.println("销售了 -- " + tickets.remove(0));
				}
			}).start();
		}
	}

}
