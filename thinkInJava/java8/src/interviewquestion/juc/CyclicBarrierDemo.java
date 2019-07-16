package interviewquestion.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author wcg
 * @CreateDate 2019/7/8-21:26
 */
public class CyclicBarrierDemo {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, new Runnable() {
            @Override
            public void run() {
                System.out.println("�ٻ�����***");

            }
        });


        for (int i = 1; i <= 7; i++) {
            final int tempI = i;
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + " \t �ռ�����: " + tempI + "������");
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }, String.valueOf(i)
            ).start();
        }


    }
}
