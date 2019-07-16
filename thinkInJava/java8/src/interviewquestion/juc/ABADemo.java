package interviewquestion.juc;

import javax.print.attribute.standard.Sides;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author wcg
 * @CreateDate 2019/7/5-18:04
 */
public class ABADemo { //ABA����Ľ��  AtomicStampedReference

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);


    public static void main(String[] args) {

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();


        new Thread(() -> {
            //��ͣһ����t2�߳�,��֤�����t1�߳������һ��ABA����
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        //��ͣһ���߳�
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====================������ABA����Ľ��========================");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t��1�ΰ汾��:" + stamp);
            //��ͣһ����t3�߳�
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t��2�ΰ汾��:" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "\t��3�ΰ汾��:" + atomicStampedReference.getStamp());


        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t��1�ΰ汾��:" + stamp);
            //��ͣһ����t3�߳�
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           Boolean result = atomicStampedReference.compareAndSet(100,2019,
                   stamp,stamp+1);
            atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t �޸ĳɹ���:"+result + "\t ��ǰ����ʵ�ʰ汾��:"+atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName() + "\t ��ǰʵ������ֵ:"+atomicStampedReference.getReference());


        }, "t4").start();


    }
}
