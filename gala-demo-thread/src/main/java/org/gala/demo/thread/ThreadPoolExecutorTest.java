package org.gala.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuan.li
 *
 *
 *         1. workers workers是HashSet
 *         <Work>类型，即它是一个Worker集合。而一个Worker对应一个线程，也就是说线程池通过workers包含了"一个线程集合"。
 *         当Worker对应的线程池启动时，它会执行线程池中的任务；当执行完一个任务后， 它会从线程池的阻塞队列中取出一个阻塞的任务来继续运行。
 *         wokers的作用是，线程池通过它实现了"允许多个线程同时运行"。
 * 
 *         2. workQueue workQueue是BlockingQueue类型，即它是一个阻塞队列。当线程池中的线程数超过它的容量的时候，
 *         线程会进入阻塞队列进行阻塞等待。 通过workQueue，线程池实现了阻塞功能。
 * 
 *         3. mainLock mainLock是互斥锁，通过mainLock实现了对线程池的互斥访问。
 * 
 *         4. corePoolSize和maximumPoolSize
 *         corePoolSize是"核心池大小"，maximumPoolSize是"最大池大小"。
 *         它们的作用是调整"线程池中实际运行的线程的数量"。 例如，当新任务提交给线程池时(通过execute方法)。 --
 *         如果此时，线程池中运行的线程数量< corePoolSize，则创建新线程来处理请求。 -- 如果此时，线程池中运行的线程数量>
 *         corePoolSize，但是却< maximumPoolSize；则仅当阻塞队列满时才创建新线程。 如果设置的 corePoolSize
 *         和 maximumPoolSize 相同，则创建了固定大小的线程池。如果将 maximumPoolSize 设置为基本的无界值（如
 *         Integer.MAX_VALUE），则允许池适应任意数量的并发任务。在大多数情况下，核心池大小和最大池大小的值是在创建线程池设置的；但是
 *         ，也可以使用 setCorePoolSize(int) 和 setMaximumPoolSize(int) 进行动态更改。
 * 
 *         5. poolSize poolSize是当前线程池的实际大小，即线程池中任务的数量。
 * 
 *         6. allowCoreThreadTimeOut和keepAliveTime
 *         allowCoreThreadTimeOut表示是否允许"线程在空闲状态时，仍然能够存活"；
 *         而keepAliveTime是当线程池处于空闲状态的时候，超过keepAliveTime时间之后，空闲的线程会被终止。
 * 
 *         7. threadFactory
 *         threadFactory是ThreadFactory对象。它是一个线程工厂类，"线程池通过ThreadFactory创建线程"。
 * 
 *         8. handler handler是RejectedExecutionHandler类型。它是"线程池拒绝策略"的句柄，
 *         也就是说"当某任务添加到线程池中，而线程池拒绝该任务时，线程池会通过handler进行相应的处理"。
 *
 *         线程池共包括4种拒绝策略，它们分别是：AbortPolicy, CallerRunsPolicy,
 *         DiscardOldestPolicy和DiscardPolicy。 AbortPolicy -- 当任务添加到线程池中被拒绝时，它将抛出
 *         RejectedExecutionException 异常。默认 CallerRunsPolicy --
 *         当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。 DiscardOldestPolicy --
 *         当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。 DiscardPolicy
 *         -- 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
 *
 */
public class ThreadPoolExecutorTest {

	private static final int THREADS_SIZE = 5;
	private static final int CAPACITY = 10;

	public static void main(String[] args) throws Exception {

		// 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
		ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(CAPACITY));
		// 设置线程池的拒绝策略为"抛出异常"
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

		try {

			// 新建10个任务，并将它们添加到线程池中。
			for (int i = 0; i < 10; i++) {
				Runnable myrun = new MyRunnable("task-" + i);
				pool.execute(myrun);
			}
		} catch (RejectedExecutionException e) {
			e.printStackTrace();
			// 关闭线程池
			pool.shutdown();
		}
	}
}

class MyRunnable implements Runnable {
	private String name;

	public MyRunnable(String name) {
		this.name = name;
	}

	public void run() {
		try {
			System.out.println(this.name + " is running.");
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
