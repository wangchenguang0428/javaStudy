package interviewquestion.executerpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * @author wcg
 * @CreateDate 2019/7/10-15:12
 * 第四种获得/使用java多线程的方式,线程池
 */
public class MyThreadPoolDemo {
//最大线程数=maximumPoolSize + LinkedBlockingQueue<>(3)[阻塞队列里面的数量]
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //查看当前cpu核心数量

        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        //模拟10个用户来办理业务,每个用户就是一个来自外部的请求线程
        try {

            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会线程
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

        // ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池处理1个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池处理N个线程


        //模拟10个用户来办理业务,每个用户就是一个来自外部的请求线程
        try {

            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会线程
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
