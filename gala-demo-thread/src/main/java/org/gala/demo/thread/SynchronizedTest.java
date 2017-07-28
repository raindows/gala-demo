package org.gala.demo.thread;

/**
 * @author yuan.li
 * 
 */
public class SynchronizedTest implements Runnable {

	public Object obj = new Object();

	@SuppressWarnings("static-access")
	public synchronized void method1() {
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("method1:" + Thread.currentThread().getName());
	}

	@SuppressWarnings("static-access")
	public void method2() {
		synchronized (this) {
			try {
				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("method2:" + Thread.currentThread().getName());
		}
	}

	@SuppressWarnings("static-access")
	public synchronized static void method3() {
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("method3:" + Thread.currentThread().getName());
	}

	static SynchronizedTest t = new SynchronizedTest();

	@SuppressWarnings("static-access")
	public void run() {

		// t.method1();
		// t.method2();
		t.method3();
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(t);
		Thread t2 = new Thread(t);
		Thread t3 = new Thread(t);
		t1.start();
		t2.start();
		t3.start();
	}
}
