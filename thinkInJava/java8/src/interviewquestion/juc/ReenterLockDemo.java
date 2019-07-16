package interviewquestion.juc;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {

    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t invoked sendSMS() ");
        sendEmail();

    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getId() + "\t #############invoked sendEmail() ");

    }


    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void get() throws Exception {
        lock.lock();
        lock.lock();

        try {

            //线程可以进入任何一个它已经拥有的锁
            //
            //
            //所同步着的代码块
            System.out.println(Thread.currentThread().getName() + "\t  invoke get() ");
            setLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            lock.unlock();
            lock.unlock();
        }


    }

    public void setLock() throws Exception {
        lock.lock();

        try {
            System.out.println(Thread.currentThread().getName() + "\t  ########invoke set() ");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

/**
 * @author wcg
 * @CreateDate 2019/7/8-8:19
 * 可重入锁（也叫做递归锁）
 * 指的是同一线程外层函数获得锁之后,内层递归函数仍然能获取该锁的代码
 * 在同一个线程在外层方法获取该锁的时候,在进入内层方法会自动获取该锁
 * <p>
 * 也即是说,线程可以进入任何它已经拥有的锁同步着的代码块
 * <p>
 * <p>
 * case one synchronized就是一个典型的可重入锁ReenterLock
 * t1	 invoked sendSMS()                      t1线程在外层获取方法锁的时候
 * t1	 #############invoked sendEmail()       t1在进入内层方法的时候会自动获取锁
 * <p>
 * <p>
 * t2	 invoked sendSMS()                      t2线程在外层获取方法锁的时候
 * t2	 #############invoked sendEmail()       t2在进入内层方法的时候会自动获取锁
 * <p>
 * case two 也是一个典型的可重入锁
 */
public class ReenterLockDemo {


    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();
        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();



        Thread t3 = new Thread(phone,"t3");
        Thread t4 = new Thread(phone,"t4");

        t3.start();
        t4.start();


    }


}
