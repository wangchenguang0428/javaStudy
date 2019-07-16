package com.atguigu.juc02;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * һ���̳߳أ��ṩ��һ���̶߳��У������б��������еȴ�״̬���̡߳������˴��������ٶ��⿪�����������Ӧ���ٶȡ�
 *
 * �����̳߳ص���ϵ�ṹ��
 * 	java.util.concurrent.Executor : �����̵߳�ʹ������ȵĸ��ӿ�
 * 		|--**ExecutorService �ӽӿ�: �̳߳ص���Ҫ�ӿ�
 * 			|--ThreadPoolExecutor �̳߳ص�ʵ����
 * 			|--ScheduledExecutorService �ӽӿڣ������̵߳ĵ���
 * 				|--ScheduledThreadPoolExecutor ���̳� ThreadPoolExecutor�� ʵ�� ScheduledExecutorService
 *
 * ���������� : Executors
 * ExecutorService newFixedThreadPool() : �����̶���С���̳߳�
 * ExecutorService newCachedThreadPool() : �����̳߳أ��̳߳ص��������̶������Ը��������Զ��ĸ���������
 * ExecutorService newSingleThreadExecutor() : ���������̳߳ء��̳߳���ֻ��һ���߳�
 *
 * ScheduledExecutorService newScheduledThreadPool() : �����̶���С���̣߳������ӳٻ�ʱ��ִ������
 */
public class TestThreadPool {

    public static void main(String[] args) {
        //1.�����̳߳�
        ExecutorService pool = Executors.newFixedThreadPool(5);


        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>(){

                @Override
                public Integer call() throws Exception {
                    int sum = 0;

                    for (int i = 0; i <= 100; i++) {
                        sum += i;
                    }

                    return sum;
                }

            });

            list.add(future);
        }

        pool.shutdown();

        for (Future<Integer> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }







//        ThreadPoolDemo tpd = new ThreadPoolDemo();
//
//        //2. Ϊ�̳߳��е��̷߳�������
//        for (int i = 0; i < 10; i++) {
//            pool.submit(tpd);
//        }
//
//        //3.�ر��̳߳�
//        pool.shutdown();




    }
}


class ThreadPoolDemo implements Runnable {
    private int i = 0;
    @Override
    public void run() {
        while (i<100){
            System.out.println(Thread.currentThread().getName()+" : "+i++);
        }

    }
}
