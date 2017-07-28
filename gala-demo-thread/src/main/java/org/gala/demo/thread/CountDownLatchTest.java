package org.gala.demo.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author yuan.li
 *
 *         ountDownLatch是通过“共享锁”实现的。在创建CountDownLatch中时，会传递一个int类型参数count，该参数是“
 *         锁计数器”的初始状态，表示该“共享锁”最多能被count给线程同时获取。
 *         当某线程调用该CountDownLatch对象的await()方法时，该线程会等待“共享锁”可用时，才能获取“共享锁”进而继续运行。而“
 *         共享锁”可用的条件，就是“锁计数器”的值为0！而“锁计数器”的初始值为count，
 *         每当一个线程调用该CountDownLatch对象的countDown()方法时，才将“锁计数器”-1；通过这种方式，
 *         必须有count个线程调用countDown()之后，“锁计数器”才为0，而前面提到的等待线程才能继续运行！
 *
 *         CountDownLatch和CyclicBarrier的区别 (01)
 *         CountDownLatch的作用是允许1或N个线程等待其他线程完成执行；而CyclicBarrier则是允许N个线程相互等待。 (02)
 *         CountDownLatch的计数器无法被重置；CyclicBarrier的计数器可以被重置后使用，因此它被称为是循环的barrier。
 *
 *         下面通过CountDownLatch实现："主线程"等待"5个子线程"全部都完成"指定的工作(休眠1000ms)"之后，再继续运行
 *
 */
public class CountDownLatchTest {

	private static int LATCH_SIZE = 5;
	private static CountDownLatch doneSignal;

	public static void main(String[] args) {

		try {
			doneSignal = new CountDownLatch(LATCH_SIZE);

			// 新建5个任务
			for (int i = 0; i < LATCH_SIZE; i++)
				new InnerThread().start();

			System.out.println("main await begin.");
			// "主线程"等待线程池中5个任务的完成
			doneSignal.await();

			System.out.println("main await finished.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class InnerThread extends Thread {
		public void run() {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
				// 将CountDownLatch的数值减1
				doneSignal.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
