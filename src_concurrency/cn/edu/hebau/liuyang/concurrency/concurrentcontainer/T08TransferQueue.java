package cn.edu.hebau.liuyang.concurrency.concurrentcontainer;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 注意需要先起消费者
 * transfer() 必须有人来处理，否则阻塞。
 * @author liuyang
 *
 */
public class T08TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		TransferQueue<String> strs = new LinkedTransferQueue<>();
		
		// 先起消费者则可正常进行
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}) .start();

		TimeUnit.SECONDS.sleep(5);
		strs.transfer("hello, TransferQeuue transfer()");// 如果此时没有消费者，则就会阻塞
		
		
		// 如果后起消费者，则会阻塞无法执行。
		//strs.transfer("hello, TransferQeuue transfer()");// 使用transfer则就会阻塞，后面无法执行
//		strs.put("hello, TransferQeuue put()");// 这个就可以继续执行
//		new Thread(()->{
//			try {
//				System.out.println(strs.take());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}) .start();
	}
}
