package interviewquestion.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author wcg
 * @CreateDate 2019/7/8-18:50
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6); //倒计数

        for (int i = 1; i <= 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t 国,被灭  ");
                        countDownLatch.countDown();

                    }, CountryEnum.foreach_CountryEnum(i).getReturnMsg()
            ).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t**************************秦帝国一统华夏");



    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6); //倒计数

        for (int i = 1; i <= 6; i++) {
            new Thread(
                    () -> {
                        System.out.println(Thread.currentThread().getName() + "\t 上晚自习离开教室");
                        countDownLatch.countDown();

                    }, String.valueOf(i)
            ).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t**************************88888班长最后关门走人!");
    }
}
