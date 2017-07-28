package org.gala.demo.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author yuan.li
 * 
 *         线程进入读锁的前提条件： 没有其他线程的写锁， 没有写请求或者有写请求，但调用线程和持有锁的线程是同一个
 * 
 *         线程进入写锁的前提条件： 没有其他线程的读锁 没有其他线程的写锁
 *
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		// 创建账户
		MyCount myCount = new MyCount("4238920615242830", 10000);
		// 创建用户，并指定账户
		User user = new User("Tommy", myCount);

		// 分别启动3个“读取账户金钱”的线程 和 3个“设置账户金钱”的线程
		for (int i = 0; i < 3; i++) {
			user.getCash();
			user.setCash((i + 1) * 1000);
		}
	}
}

class User {
	
	private String name; // 用户名
	private MyCount myCount; // 所要操作的账户
	private ReadWriteLock myLock; // 执行操作所需的锁对象

	User(String name, MyCount myCount) {
		this.name = name;
		this.myCount = myCount;
		this.myLock = new ReentrantReadWriteLock();
	}

	public void getCash() {
		new Thread() {
			public void run() {
				myLock.readLock().lock();
				try {
					System.out.println(Thread.currentThread().getName() + " getCash start");
					myCount.getCash();
					Thread.sleep(1);
					System.out.println(Thread.currentThread().getName() + " getCash end");
				} catch (InterruptedException e) {
				} finally {
					myLock.readLock().unlock();
				}
			}
		}.start();
	}

	public void setCash(final int cash) {
		new Thread() {
			public void run() {
				myLock.writeLock().lock();
				try {
					System.out.println(Thread.currentThread().getName() + " setCash start");
					myCount.setCash(cash);
					Thread.sleep(1);
					System.out.println(Thread.currentThread().getName() + " setCash end");
				} catch (InterruptedException e) {
				} finally {
					myLock.writeLock().unlock();
				}
			}
		}.start();
	}
}

class MyCount {
	private String id; // 账号
	private int cash; // 账户余额

	MyCount(String id, int cash) {
		this.id = id;
		this.cash = cash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCash() {
		System.out.println(Thread.currentThread().getName() + " getCash cash=" + cash);
		return cash;
	}

	public void setCash(int cash) {
		System.out.println(Thread.currentThread().getName() + " setCash cash=" + cash);
		this.cash = cash;
	}
}