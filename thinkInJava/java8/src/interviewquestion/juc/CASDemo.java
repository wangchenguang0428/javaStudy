package interviewquestion.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS是什么?
 * 比较并交换
 *
 * @author wcg
 * @CreateDate 2019/7/5-14:38
 */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);


    //main do things.......
        System.out.println(atomicInteger.compareAndSet(5,
                2019) + "\t current data: " +atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,
                1024) + "\t current data: " +atomicInteger.get());

}


}
