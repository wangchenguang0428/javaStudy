package interviewquestion.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/7/9-14:03
 * <p>
 * ��������SynchronousQueue��ʾ(ͬ������)
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();


        new Thread(() -> {
            try {
                //��ͣһ���߳�
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }, "BBB").start();


    }


}
