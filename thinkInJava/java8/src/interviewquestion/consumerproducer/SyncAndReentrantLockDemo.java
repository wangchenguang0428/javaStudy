package interviewquestion.consumerproducer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wcg
 * @CreateDate 2019/7/9-15:25
 * ��Ŀ: sychronized��lock��ʲô����?���µ�lock��ʲô�ô�?�����˵˵
 * 1.ԭʼ����:
 * sychronized��java�Ĺؼ�������jvm����
 * monitorenter(�ײ���ͨ��monitor��������ɵ�,��ʵwait/notify�ȷ���Ҳ��������monitor����ֻ����ͬ������߷����в��ܵ���wait/notify�ȷ���)
 * monitorexit
 * lock��java5�Ժ��¼ӵ�һ���� lock�Ǿ������(java.util.concurrent.locks.lock)��api�������
 * <p>
 * <p>
 * 2.ʹ�÷���
 * sychronized����Ҫ�û�ȥ�ֶ����ͷ���,��sychronized����ִ����ɺ�ϵͳ���Զ����߳��ͷŶ�����ռ��
 * ReentrantLock����Ҫ�û�ȥ�ֶ��ͷ�����û�������ͷ���,���п��ܵ��³�����������
 * ��Ҫlock��unlock�������try/finally���������
 * <p>
 * <p>
 * 3.�ȴ��Ƿ���ж�
 * sychronized�����ж�,�����׳��쳣���������������
 * ReentrantLock���ж�,1.���ó�ʱ����tryLock(long timeout, TimeUnit unit)
 * 2.lockinterruptibly()�Ŵ������,����interrupt()�������ж�
 * <p>
 * 4.�����Ƿ�ƽ
 * sychronized�ǹ�ƽ��
 * ReentrantLock���߶�����,Ĭ�Ϸǹ�ƽ��,���췽�����Դ���booleanֵ��trueΪ��ƽ��,falseΪ�ǹ�ƽ��
 * 5.�󶨶������Condition
 * sychronizedû��
 * ReentrantLock����ʵ�ַ��黽����Ҫ���ѵ��߳���,���Ծ�ȷ����,��������sychronizedҪô�������һ���߳�,Ҫô����ȫ���߳�
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * ====================================================================================
 * <p>
 * ��Ŀ:���߳�֮�䰴˳�����,ʵ��A-B-C�����߳�����,Ҫ������:
 * AA��ӡ5��,BB��ӡ10��,CC��ӡ15��
 * ������
 * AA��ӡ5��,BB��ӡ10��,CC��ӡ15��
 * ........
 * ��10��
 */

class ShareResource {

    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    //1.�ж�
    public void print5() {

        lock.lock();
        try {

            //1.�ж�
            while (number != 1) {
                c1.await();
            }
            //2.�ɻ�
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }

            //3.֪ͨ
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }

    }

    //1.�ж�
    public void print10() {

        lock.lock();
        try {

            //1.�ж�
            while (number != 2) {
                c2.await();
            }
            //2.�ɻ�
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }
            //3.֪ͨ
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

    //1.�ж�
    public void print15() {
        lock.lock();
        try {

            //1.�ж�
            while (number != 3) {
                c3.await();
            }
            //2.�ɻ�
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t " + number);
            }
            //3.֪ͨ
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 15; i++) {
                shareResource.print15();
            }
        }, "CC").start();


    }
}
