package com.atguigu.juc03;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author wcg
 * @CreateDate 2019/6/18-15:39
 */
public class TwinsLockTest {
    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
// ����10���߳�
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
// ÿ��1�뻻��
        for (int i = 0; i < 10; i++) {
            SleepUtils.second(1);
//            System.out.println();
        }
    }
}
