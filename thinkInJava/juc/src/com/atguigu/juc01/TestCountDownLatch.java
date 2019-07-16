package com.atguigu.juc01;


import java.util.concurrent.CountDownLatch;

/*
 * CountDownLatch �������������ĳЩ�����ǣ�ֻ�����������̵߳�����ȫ����ɣ���ǰ����ż���ִ��
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(10);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(ld).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("����ʱ��:" + (end - start));

    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;


    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;

    }

    @Override
    public void run() {

        synchronized (this) {

            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                latch.countDown();
            }
        }


    }
}
