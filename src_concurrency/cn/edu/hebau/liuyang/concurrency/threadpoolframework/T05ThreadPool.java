package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(5); //
		for (int i = 0; i < 6; i++) {
			service.execute(() ->{
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}); 
		}
		
		System.out.println(service);
		
		service.shutdown();// shutdown transactional
		//service.shutdownNow(); // shutdown abort
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
	}

}
