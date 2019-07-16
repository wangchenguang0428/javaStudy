package com.atguigu.juc02;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ��дһ�����򣬿��� 3 ���̣߳��������̵߳� ID �ֱ�Ϊ A��B��C��ÿ���߳̽��Լ��� ID ����Ļ�ϴ�ӡ 10 �飬Ҫ������Ľ�����밴˳����ʾ��
 *	�磺ABCABCABC���� ���εݹ�
 */
public class TestABCAlternate {

    public static void main(String[] args) {
        final AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopA(i);
                }

            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopB(i);
                }

            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 1; i <= 20; i++) {
                    ad.loopC(i);

                    System.out.println("-----------------------------------");
                }

            }
        }, "C").start();
    }



}

class AlternateDemo{

    private int number = 1; //��ǰ����ִ���̵߳ı��

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    /**
     * @param totalLoop : ѭ���ڼ���
     */
    public void loopA(int totalLoop){
        lock.lock();

        try {
            //1. �ж�
            if(number != 1){
                condition1.await();
            }

            //2. ��ӡ
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. ����
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(int totalLoop){
        lock.lock();

        try {
            //1. �ж�
            if(number != 2){
                condition2.await();
            }

            //2. ��ӡ
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. ����
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(int totalLoop){
        lock.lock();

        try {
            //1. �ж�
            if(number != 3){
                condition3.await();
            }

            //2. ��ӡ
            for (int i = 1; i <= 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoop);
            }

            //3. ����
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

