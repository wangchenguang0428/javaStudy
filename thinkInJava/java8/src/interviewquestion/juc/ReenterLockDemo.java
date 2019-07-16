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

            //�߳̿��Խ����κ�һ�����Ѿ�ӵ�е���
            //
            //
            //��ͬ���ŵĴ����
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
 * ����������Ҳ�����ݹ�����
 * ָ����ͬһ�߳���㺯�������֮��,�ڲ�ݹ麯����Ȼ�ܻ�ȡ�����Ĵ���
 * ��ͬһ���߳�����㷽����ȡ������ʱ��,�ڽ����ڲ㷽�����Զ���ȡ����
 * <p>
 * Ҳ����˵,�߳̿��Խ����κ����Ѿ�ӵ�е���ͬ���ŵĴ����
 * <p>
 * <p>
 * case one synchronized����һ�����͵Ŀ�������ReenterLock
 * t1	 invoked sendSMS()                      t1�߳�������ȡ��������ʱ��
 * t1	 #############invoked sendEmail()       t1�ڽ����ڲ㷽����ʱ����Զ���ȡ��
 * <p>
 * <p>
 * t2	 invoked sendSMS()                      t2�߳�������ȡ��������ʱ��
 * t2	 #############invoked sendEmail()       t2�ڽ����ڲ㷽����ʱ����Զ���ȡ��
 * <p>
 * case two Ҳ��һ�����͵Ŀ�������
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
        //��ͣһ���߳�
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
