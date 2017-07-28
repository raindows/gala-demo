package org.gala.demo.thread;

/**
 * @author yuan.li
 *
 */
public class MyThread implements Runnable {

	private int ticket = 10;

	public void run() {
		for (int i = 0; i < 200; i++) {
			if (this.ticket > 0) {
				System.out.println(Thread.currentThread().getName() + " 卖票：ticket" + this.ticket--);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		MyThread mt = new MyThread();
		Thread t1 = new Thread(mt);
		Thread t2 = new Thread(mt);
		Thread t3 = new Thread(mt);
		t1.start();
		t2.start();
		t3.start();
	}
}
