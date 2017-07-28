package org.gala.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuan.li
 *
 *         Condition的作用是对锁进行更精确的控制。Condition中的await()方法相当于Object的wait()方法，
 *         Condition中的signal()方法相当于Object的notify()方法，
 *         Condition中的signalAll()相当于Object的notifyAll()方法。不同的是，Object中的wait(),
 *         notify(),notifyAll()方法是和"同步锁"(synchronized关键字)捆绑使用的
 *         ；而Condition是需要与"互斥锁"/"共享锁"捆绑使用的。
 *
 *         通过Condition的await(), signal()来演示线程的休眠/唤醒功能。
 *
 */
public class ConditionTest {

	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();

	public static void main(String[] args) {

		ThreadA ta = new ThreadA("ta");

		lock.lock(); // 获取锁
		try {
			System.out.println(Thread.currentThread().getName() + " start ta");
			ta.start();

			System.out.println(Thread.currentThread().getName() + " block");
			condition.await(); // 等待

			System.out.println(Thread.currentThread().getName() + " continue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock(); // 释放锁
		}
	}

	static class ThreadA extends Thread {

		public ThreadA(String name) {
			super(name);
		}

		public void run() {
			lock.lock(); // 获取锁
			try {
				System.out.println(Thread.currentThread().getName() + " wakup others");
				condition.signal(); // 唤醒“condition所在锁上的其它线程”
			} finally {
				lock.unlock(); // 释放锁
			}
		}
	}
}
