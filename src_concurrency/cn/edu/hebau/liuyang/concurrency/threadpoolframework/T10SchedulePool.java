package cn.edu.hebau.liuyang.concurrency.threadpoolframework;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Timer每次new一个新的线程。而ScheduledThreadPool中的线程可以复用。<br>
 * 
 * @author liuyang
 *
 */
public class T10SchedulePool {

	/**
	 * Creates and executes a periodic action that becomes enabled first after the
	 * given initial delay, and subsequently with the given period; that is
	 * executions will commence after {@code initialDelay} then
	 * {@code initialDelay+period}, then {@code initialDelay + 2 * period}, and so
	 * on. If any execution of the task encounters an exception, subsequent
	 * executions are suppressed. Otherwise, the task will only terminate via
	 * cancellation or termination of the executor. If any execution of this task
	 * takes longer than its period, then subsequent executions may start late, but
	 * will not concurrently execute.
	 *
	 * @param command
	 *            the task to execute
	 * @param initialDelay
	 *            the time to delay first execution
	 * @param period
	 *            the period between successive executions
	 * @param unit
	 *            the time unit of the initialDelay and period parameters
	 * @return a ScheduledFuture representing pending completion of the task, and
	 *         whose {@code get()} method will throw an exception upon cancellation
	 * @throws RejectedExecutionException
	 *             if the task cannot be scheduled for execution
	 * @throws NullPointerException
	 *             if command is null
	 * @throws IllegalArgumentException
	 *             if period less than or equal to zero
	 */

	private static final int CORE_POOL_SIZE = 4;
	private static final long INITIAL_DELAY = 0;// 初始延迟
	private static final long PERIOD = 500; // 执行间隔
	private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;

	public static void main(String[] args) throws InterruptedException {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
		service.scheduleAtFixedRate(() -> { // 以固定频率执行某个任务
			try {
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("hey! " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
		}, INITIAL_DELAY, PERIOD, UNIT);

		TimeUnit.SECONDS.sleep(20);// 给程序运行以足够时间，要不看不到效果。
		service.shutdown();
	}

}
