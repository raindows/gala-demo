this逃逸
是指在构造函数返回之前其他线程就持有该对象的引用. 调用尚未构造完全的对象的方法可能引发令人疑惑的错误, 因此应该避免this逃逸的发生.

当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。

final修饰的引用类型，引用不可以改变，但是它所指向的对象的内容可以改变

动态代理就是利用java中的Proxy和Invocationhandler实现的。实现动态代理你可以在某个类的每个方法执行前后加上你想要执行的代码。
比如，你想要测试方法执行时间，那么你可以写个动态代理，在想要测试的方法执行前获得时间然后在方法执行完后再获取时间，取两次时间差便可得要测试方法的执行时间。
仅用文字描述难免让你觉得抽象，何况动态代理本身就是挺抽象的概念。在此，你要记住一条，动态代理就是对被代理的类进行一次封装包裹以便加上你需要加的业务逻辑。

AtomicInteger基于冲突检测的乐观并发策略。 可以想象，这种乐观在线程数目非常多的情况下，失败的概率会指数型增加。
CAS(就是Compare and Swap的意思，比较并操作)有3个操作数，内存值V，预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。

volatile
将当前处理器缓存行的数据会写回到系统内存。
这个写回内存的操作会引起在其他CPU里缓存了该内存地址的数据无效。

synchronized，wait，sleep，join，yeid，interrupt
1,wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。
wait和notify是Object类中有的方法，当一个线程中一个对象调用这个方法的时候这个线程就会进入等待状态，等待别的线程用相同对象调用notify唤醒。
2,我们在ThreadA线程运行ThreadB.join()，这个时候ThreadA就休眠直到ThreadB运行完毕。join()就相当于是将ThreadA未执行的代码放到ThreadB后面执行。
3,yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
yield() 与 wait()的比较:
我们知道，wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，也会释放同步锁。而yield()的作用是让步，它也会让当前线程离开“运行状态”。它们的区别是：
(01) wait()是让线程由“运行状态”进入到“等待(阻塞)状态”，而不yield()是让线程由“运行状态”进入到“就绪状态”。
(02) wait()是会线程释放它所持有对象的同步锁，而yield()方法不会释放锁。
4,sleep() 与 wait()的比较
我们知道，wait()的作用是让当前线程由“运行状态”进入“等待(阻塞)状态”的同时，也会释放同步锁。而sleep()的作用是也是让当前线程由“运行状态”进入到“休眠(阻塞)状态”。
但是，wait()会释放对象的同步锁，而sleep()则不会释放锁
5,interrupt()的作用是中断本线程。
本线程中断自己是被允许的；其它线程调用本线程的interrupt()方法时，会通过checkAccess()检查权限。这有可能抛出SecurityException异常。
如果本线程是处于阻塞状态：调用线程的wait(), wait(long)或wait(long, int)会让它进入等待(阻塞)状态，或者调用线程的join(), join(long), join(long, int), sleep(long), sleep(long, int)也会让它进入阻塞状态。若线程在阻塞状态时，调用了它的interrupt()方法，那么它的“中断状态”会被清除并且会收到一个InterruptedException异常。例如，线程通过wait()进入阻塞状态，此时通过interrupt()中断该线程；调用interrupt()会立即将线程的中断标记设为“true”，但是由于线程处于阻塞状态，所以该“中断标记”会立即被清除为“false”，同时，会产生一个InterruptedException的异常。
如果线程被阻塞在一个Selector选择器中，那么通过interrupt()中断它时；线程的中断标记会被设置为true，并且它会立即从选择操作中返回。
如果不属于前面所说的情况，那么通过interrupt()中断线程时，它的中断标记会被设置为“true”。
中断一个“已终止的线程”不会产生任何操作。

Thread， Runnable，Callable
public interface Runnable {
    public abstract void run();
}
public class Thread implements Runnable
public interface Callable<V> {
    V call() throws Exception;
}
Callable接口与Runnable接口相比，只是Callable接口可以返回值而已。

Thread的start()和run()的区别：
start():它的作用是启动一个新线程，新线程会执行相应的run()方法。start()不能被重复调用。
run():run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程

synchronized的基本规则总结为下面3条
第一条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
第二条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程仍然可以访问“该对象”的非同步代码块。
第三条: 当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，其他线程对“该对象”的其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
实例锁 -- 锁在某一个实例对象上。如果该类是单例，那么该锁也具有全局锁的概念。
               实例锁对应的就是synchronized关键字。
全局锁 -- 该锁针对的是类，无论实例多少个对象，那么线程都共享该锁。
               全局锁对应的就是static synchronized（或者是锁在该类的class或者classloader对象上）。
关于“实例锁”和“全局锁”有一个很形象的例子：
pulbic class Something {
    public synchronized void isSyncA(){}
    public synchronized void isSyncB(){}
    public static synchronized void cSyncA(){}
    public static synchronized void cSyncB(){}
}
假设，Something有两个实例x和y。分析下面4组表达式获取的锁的情况。
(01) x.isSyncA()与x.isSyncB() 
(02) x.isSyncA()与y.isSyncA()
(03) x.cSyncA()与y.cSyncB()
(04) x.isSyncA()与Something.cSyncA()
(01) 不能被同时访问。因为isSyncA()和isSyncB()都是访问同一个对象(对象x)的同步锁！
(02) 可以同时被访问。因为访问的不是同一个对象的同步锁，x.isSyncA()访问的是x的同步锁，而y.isSyncA()访问的是y的同步锁。
(03) 不能被同时访问。因为cSyncA()和cSyncB()都是static类型，x.cSyncA()相当于Something.isSyncA()，y.cSyncB()相当于Something.isSyncB()，因此它们共用一个同步锁，不能被同时反问。
(04) 可以被同时访问。因为isSyncA()是实例方法，x.isSyncA()使用的是对象x的锁；而cSyncA()是静态方法，Something.cSyncA()可以理解对使用的是“类的锁”。因此，它们是可以被同时访问的。

可重入的意思是，锁，可以被单个线程多次获取。

http://www.cnblogs.com/skywang12345/p/java_threads_category.html