package interviewquestion.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache { //��Դ��

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object val) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t ����д��:" + key);
            //��ͣһ���߳�
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, val);
            System.out.println(Thread.currentThread().getName() + "\t д�����");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();

        }

    }

    public void get(String key) {


        try {
            reentrantReadWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t ���ڶ�ȡ:" + key);
            //��ͣһ���߳�
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object val = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t ��ȡ���:" + val);
        } finally {
            reentrantReadWriteLock.readLock().unlock();

        }

    }


}

/**
 * @author wcg
 * @CreateDate 2019/7/8-16:25
 * ����߳�ͬʱ��һ����Դ��û���κ�����,����Ϊ�����㲢����,��ȡ������ԴӦ�ÿ���ͬʱ����
 * ����
 * �����һ���߳���ȥд������Դ,�Ͳ�Ӧ���������߳̿��ԶԸ���Դ���ж�������д
 * С�ܽ�:
 * ��-���ܹ���
 * ��-д���ܹ���
 * д-д���ܹ���
 * <p>
 * д����:ԭ�� + ��ռ
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final String temp = i + "";
            new Thread(
                    () -> {
                        myCache.put(temp, temp);
                    }, String.valueOf(i)
            ).start();
        }

        for (int i = 1; i <= 5; i++) {
            final String temp = i + "";
            new Thread(
                    () -> {
                        myCache.get(temp);
                    }, String.valueOf(i)
            ).start();
        }


    }
}
