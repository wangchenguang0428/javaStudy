package interviewquestion.consumerproducer;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData { //��Դ��

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1.�ж�
            while (number != 0) {
                //�ȴ�,��������
                condition.await();
            }
            //2.�ɻ�
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.֪ͨ����
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //1.�ж�
            while (number == 0) {
                //�ȴ�,��������
                condition.await();
            }
            //2.�ɻ�
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.֪ͨ����
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}


/**
 * @author wcg
 * @CreateDate 2019/7/9-14:22
 * <p>
 * �Ϸ����߳��µ���ٻ���
 * <p>
 * ��Ŀ:һ����ʼֵΪ0�ı���,�����̶߳�����н������,һ����1һ����1 ,��5��
 * <p>
 * 1.  �߳�     ����    ��Դ��
 * 2.  �ж�     �ɻ�    ֪ͨ
 * 3.��ֹ��ٻ��ѻ���
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i =0; i<5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"AA").start();


        new Thread(()->{
            for (int i =0; i<5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"BB").start();
        new Thread(()->{
            for (int i =0; i<5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"CC").start();


        new Thread(()->{
            for (int i =0; i<5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"DD").start();


    }

}
