package interviewquestion.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/7/8-21:39
 */
public class SemaphoreDemo {


    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3); //ģ������ͣ��λ

        for (int i = 1; i <= 6; i++) {//ģ��6������
            new Thread(() -> {

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t ������λ!!!");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t ͣ��������뿪��λ!");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();

                }

            }, String.valueOf(i)).start();

        }


    }

}
