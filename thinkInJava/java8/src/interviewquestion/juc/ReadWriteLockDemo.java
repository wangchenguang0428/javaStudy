package interviewquestion.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache { //资源类

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object val) {
        try {
            reentrantReadWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, val);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } finally {
            reentrantReadWriteLock.writeLock().unlock();

        }

    }

    public void get(String key) {


        try {
            reentrantReadWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:" + key);
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object val = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + val);
        } finally {
            reentrantReadWriteLock.readLock().unlock();

        }

    }


}

/**
 * @author wcg
 * @CreateDate 2019/7/8-16:25
 * 多个线程同时读一个资源类没有任何问题,所以为了满足并发量,读取共享资源应该可以同时进行
 * 但是
 * 如果有一个线程想去写共享资源,就不应该有其他线程可以对该资源进行读或者是写
 * 小总结:
 * 读-读能共存
 * 读-写不能共存
 * 写-写不能共存
 * <p>
 * 写操作:原子 + 独占
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
