package interviewquestion.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author wcg
 * @CreateDate 2019/7/8-18:50
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6); //������

        for (int i = 1; i <= 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t ��,����  ");
                        countDownLatch.countDown();

                    }, CountryEnum.foreach_CountryEnum(i).getReturnMsg()
            ).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t**************************�ص۹�һͳ����");



    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6); //������

        for (int i = 1; i <= 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t ������ϰ�뿪����");
                        countDownLatch.countDown();

                    }, String.valueOf(i)
            ).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t**************************88888�೤����������!");
    }
}
