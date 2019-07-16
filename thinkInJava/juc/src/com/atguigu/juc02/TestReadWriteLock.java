package com.atguigu.juc02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 1. ReadWriteLock : ¶ÁÐ´Ëø
 *
 * Ð´Ð´/¶ÁÐ´ ÐèÒª¡°»¥³â¡±
 * ¶Á¶Á ²»ÐèÒª»¥³â
 *
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
       final ReadWriteLockDemo rw = new ReadWriteLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set((int) (Math.random()*101));
            }
        },"Ð´Ëø").start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    rw.set((int) (Math.random()*101));
                }
            },"Ð´Ëø"+i).start();
        }


    }
}

class ReadWriteLockDemo{

    private int number =0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //¶Á
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        }finally {
            lock.readLock().unlock();
        }
    }

    //Ð´
    public void set(int number){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName());
            this.number = number;
        }finally{
            lock.writeLock().unlock();
        }
    }

}
