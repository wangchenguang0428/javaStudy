package com.atguigu.juc03;

import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/6/17-17:12
 */
public class Interrupted {
    public static void main(String[] args) throws Exception {
// sleepThread��ͣ�ĳ���˯��
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
// busyThread��ͣ������
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
// ����5�룬��sleepThread��busyThread�������
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());
// ��ֹsleepThread��busyThread�����˳�
        SleepUtils.second(2);
    }
    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }
    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
            }
        }
    }
}