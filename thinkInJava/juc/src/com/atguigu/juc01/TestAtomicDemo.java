package com.atguigu.juc01;

import sun.security.x509.SerialNumber;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * һ��i++ ��ԭ�������⣺i++ �Ĳ���ʵ���Ϸ�Ϊ�������衰��-��-д��
 * 		  int i = 10;
 * 		  i = i++; //10
 *
 * 		  int temp = i;
 * 		  i = i + 1;
 * 		  i = temp;
 *
 * ����ԭ�ӱ������� java.util.concurrent.atomic �����ṩ��һЩԭ�ӱ�����
 * 		1. volatile ��֤�ڴ�ɼ���
 * 		2. CAS��Compare-And-Swap�� �㷨��֤���ݱ�����ԭ����
 * 			CAS �㷨��Ӳ�����ڲ���������֧��
 * 			CAS ������������������
 * 			���ڴ�ֵ  V
 * 			��Ԥ��ֵ  A
 * 			�۸���ֵ  B
 * 			���ҽ��� V == A ʱ�� V = B; ���򣬲���ִ���κβ�����
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(ad).start();
        }

    }

}

class AtomicDemo implements Runnable {

//    private volatile   int seriNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger(10);




    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":" + getSeriNumber());
    }


    public int getSeriNumber() {
        return serialNumber.getAndIncrement();

    }
}


