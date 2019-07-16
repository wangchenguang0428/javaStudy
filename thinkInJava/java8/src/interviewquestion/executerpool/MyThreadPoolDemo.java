package interviewquestion.executerpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * @author wcg
 * @CreateDate 2019/7/10-15:12
 * �����ֻ��/ʹ��java���̵߳ķ�ʽ,�̳߳�
 */
public class MyThreadPoolDemo {
//����߳���=maximumPoolSize + LinkedBlockingQueue<>(3)[�����������������]
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //�鿴��ǰcpu��������

        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        //ģ��10���û�������ҵ��,ÿ���û�����һ�������ⲿ�������߳�
        try {

            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t ����ҵ��");
                });
                //��ͣһ���߳�
//                try {
//                    TimeUnit.MILLISECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();

        }


    }

    private static void threadPoolInit() {
        //Array Arrays
        //Collection Collections
        //ThreadPoolExecutor

        // ExecutorService threadPool = Executors.newFixedThreadPool(5);//һ��5�������߳�
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//һ�ش���1���߳�
        ExecutorService threadPool = Executors.newCachedThreadPool();//һ�ش���N���߳�


        //ģ��10���û�������ҵ��,ÿ���û�����һ�������ⲿ�������߳�
        try {

            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t ����ҵ��");
                });
                //��ͣһ���߳�
//                try {
//                    TimeUnit.MILLISECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();

        }
    }

}
