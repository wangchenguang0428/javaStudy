package com.atguigu.juc03;

import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/6/17-18:19
 */
public class Join {
    public static void main(String[] args) throws Exception {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
// ÿ���߳�ӵ��ǰһ���̵߳����ã���Ҫ�ȴ�ǰһ���߳���ֹ�����ܴӵȴ��з���
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join();
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
