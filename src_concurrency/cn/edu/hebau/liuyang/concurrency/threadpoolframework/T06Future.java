package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06Future {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> task = new FutureTask<>(()->{	// 装一个Callable类型的
			TimeUnit.MILLISECONDS.sleep(500);				// 模拟执行500毫秒
			return 1000;									// 未来的某一个时刻会返回一个结果
		});
		
		new Thread(task).start();// 运行
		
		System.out.println(task.get()); // 阻塞，等着task运行完。
		
		// ********************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(()->{
			TimeUnit.MILLISECONDS.sleep(500);				// 若想完成任务需要500毫秒
			return 1;										// 返回1
		});
		//System.out.println(f.get());
		System.out.println(f.isDone());
		System.out.println(f.get());
		System.out.println(f.isDone());
		
		service.shutdownNow();
	}

}
