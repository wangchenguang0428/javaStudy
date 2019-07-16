package interviewquestion.juc;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()
                    + "\t �Լ�����:" + lockA + " \t ���Ի��: " + lockB);
//            ��ͣһ���߳�
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() +
                        "\t �Լ�����:"+lockB + "\t���Ի��: "+lockA);

            }

        }

    }
}


/**
 * @author wcg
 * @CreateDate 2019/7/11-10:12
 * ������ָ���������������ϵĽ�����ִ�й�����,
 * ��������Դ����ɵ�һ�ֻ���ȴ�������,
 * �����������������Ƕ����޷��ƽ���ȥ
 */
public class DeadLockDemo {



    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();

        /**
         *
         * linux ps -ef|grep xxx
         * windows�µ�java���г���  Ҳ������ps�Ĳ鿴���̵�����,����Ŀǰ������Ҫ�鿴��ֻ��java
         *
         *  jps = java���ps  jps -l
         *
         */

    }
}
