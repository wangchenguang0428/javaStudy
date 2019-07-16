package interviewquestion.consumerproducer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wcg
 * @CreateDate 2019/7/9-15:25
 * 题目: sychronized和lock有什么区别?用新的lock有什么好处?你举例说说
 * 1.原始构成:
 * sychronized是java的关键字属于jvm层面
 * monitorenter(底层是通过monitor对象来完成的,其实wait/notify等方法也是依赖于monitor对象只有在同步块或者方法中才能调用wait/notify等方法)
 * monitorexit
 * lock是java5以后新加的一个类 lock是具体的类(java.util.concurrent.locks.lock)是api层面的锁
 * <p>
 * <p>
 * 2.使用方法
 * sychronized不需要用户去手动的释放锁,当sychronized代码执行完成后系统会自动让线程释放对锁的占用
 * ReentrantLock则需要用户去手动释放锁若没有主动释放锁,就有可能导致出现死锁现象
 * 需要lock和unlock方法配合try/finally语句块来完成
 * <p>
 * <p>
 * 3.等待是否可中断
 * sychronized不可中断,除非抛出异常或者正常运行完成
 * ReentrantLock可中断,1.设置超时方法tryLock(long timeout, TimeUnit unit)
 * 2.lockinterruptibly()放代码块中,调用interrupt()方法可中断
 * <p>
 * 4.加锁是否公平
 * sychronized非公平锁
 * ReentrantLock两者都可以,默认非公平锁,构造方法可以传入boolean值。true为公平锁,false为非公平锁
 * 5.绑定多个条件Condition
 * sychronized没有
 * ReentrantLock用来实现分组唤醒需要唤醒的线程们,可以精确唤醒,而不是像sychronized要么随机唤醒一个线程,要么唤醒全部线程
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * ====================================================================================
 * <p>
 * 题目:多线程之间按顺序调用,实现A-B-C三个线程启动,要求如下:
 * AA打印5次,BB打印10次,CC打印15次
 * 紧接着
 * AA打印5次,BB打印10次,CC打印15次
 * ........
 * 来10轮
 */

class ShareResource {

    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    //1.判断
    public void print5() {

        lock.lock();
        try {

            //1.判断
            while (number != 1) {
                c1.await();
            }
            //2.干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }

            //3.通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }

    }

    //1.判断
    public void print10() {

        lock.lock();
        try {

            //1.判断
            while (number != 2) {
                c2.await();
            }
            //2.干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }
            //3.通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

    //1.判断
    public void print15() {
        lock.lock();
        try {

            //1.判断
            while (number != 3) {
                c3.await();
            }
            //2.干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }
            //3.通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 15; i++) {
                shareResource.print15();
            }
        }, "CC").start();


    }
}
