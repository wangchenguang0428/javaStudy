package interviewquestion.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wcg
 * @CreateDate 2019/7/8-15:19
 */
public class spinLockDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " \t come in  o(+ - +)o ");

        while (!atomicReference.compareAndSet(null, thread)) {


        }

    }


    public void unLock() {

        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args) {

        spinLockDemo spinLockDemo = new spinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            //暂停一会儿xian'cheng
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.unLock();

        }, "AA").start();

        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {

            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            spinLockDemo.unLock();

        }, "BB").start();


    }


}
