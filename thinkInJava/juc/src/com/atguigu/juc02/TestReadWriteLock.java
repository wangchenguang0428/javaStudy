package com.atguigu.juc02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 1. ReadWriteLock : ��д��
 *
 * дд/��д ��Ҫ�����⡱
 * ���� ����Ҫ����
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
        },"д��").start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    rw.set((int) (Math.random()*101));
                }
            },"д��"+i).start();
        }


    }
}

class ReadWriteLockDemo{

    private int number =0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    //��
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        }finally {
            lock.readLock().unlock();
        }
    }

    //д
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
