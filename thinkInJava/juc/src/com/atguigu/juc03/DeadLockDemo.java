package com.atguigu.juc03;

/**
 * @author wcg
 * @CreateDate 2019/6/16-22:27
 */
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";
    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
    private void deadLock() {

        for(int i=0;i<1000;i++) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (A) {
//                        try {
//                            Thread.currentThread().sleep(200);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
            synchronized (B) {
                System.out.println("1");
            }
        }
    }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (B) {
                        synchronized (A) {
                            System.out.println("2");
                        }
                    }
                }
            });

            t1.start();
            t2.start();
        }

    }
}
