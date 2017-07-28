package org.gala.demo.thread.group3;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yuan.li
 * 
 *         CyclicBarrier是一个同步辅助类，允许一组线程互相等待，直到到达某个公共屏障点 (common barrier
 *         point)。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * 
 *         CyclicBarrier是通过ReentrantLock(独占锁)和Condition来实现的
 *
 *         新建5个线程，这5个线程达到一定的条件时，它们才继续往后运行。
 *         主线程中新建了5个线程，所有的这些线程都调用cb.await()等待。所有这些线程一直等待，直到cb中所有线程都达到barrier时，
 *         这些线程才继续运行！
 *
 */
public class CyclicBarrierTest {

	private static int SIZE = 5;
	private static CyclicBarrier cb;

	public static void main(String[] args) {

		cb = new CyclicBarrier(SIZE);

		// 新建5个任务
		for (int i = 0; i < SIZE; i++)
			new InnerThread().start();
	}

	static class InnerThread extends Thread {
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier.");

				// 将cb的参与者数量加1
				cb.await();

				// cb的参与者数量等于5时，才继续往后执行
				System.out.println(Thread.currentThread().getName() + " continued.");
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
