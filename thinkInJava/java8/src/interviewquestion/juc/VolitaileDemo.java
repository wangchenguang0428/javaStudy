package interviewquestion.juc;

import java.sql.Time;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wcg
 * @CreateDate 2019/7/3-14:46
 */


class Mydata { //MyData.java -> Mydata.class ->JVM�ֽ���

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;

    }

    //��ע��,number��ʱǰ���Ǽ���volatile�ؼ������ε�, volatile����֤ԭ����
    public  void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }



}

/**
 * 1.��֤volatile�Ŀɼ���
 * 1.1 ����int number = 0; number����֮ǰ����û�����volitaile�ؼ�������,û�пɼ���
 * 1.2 �����volatile,���Խ�����ɼ�������
 * 2.��֤volatile����֤ԭ����
 * 2.1 ԭ����ָ����ʲô��˼?
 * ���ɷָ�,������, Ҳ��ĳ���߳���������ҵ���ʱ��,�м䲻���Ա��������߱��ָ�.��Ҫ��������
 * Ҫôͬʱ�ɹ�,Ҫôͬʱʧ��
 * 2.2 �Ƿ���Ա�֤ԭ���԰�����ʾ
 *
 * 2.3 why
 *
 * 2.4 ��ν��ԭ����?
 *    ��sychronized
 *    ʹ������juc�µ�AtomicInteger
 */
public class VolitaileDemo {

    //ԭ����:��������һ�����ǲ����ܹ��õ���֤,�м䲻Ҫ������
    public static void main(String[] args) {//mainֻ��һ�з������������

        Mydata mydata = new Mydata();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    mydata.addPlusPlus();
                    mydata.addAtomic();
                }

            }, String.valueOf(i)).start();
        }
        //��Ҫ�ȴ�����20���߳�ȫ��������ɺ�,����main�߳�ȡ�����յĽ��ֵ���Ƕ���
        while (Thread.activeCount() > 2) {//Ϊʲô�������߳�?��Ϊ��̨�������߳�(һ����main�߳�,һ����gc�߳�)
            Thread.yield();

        }

        System.out.println(Thread.currentThread().getName()+" \t int type, finally number value:" + mydata.number);
        System.out.println(Thread.currentThread().getName()+" \t AtomicInteger type, finally number value:" + mydata.atomicInteger);

    }


    //volatile���Ա�֤�ɼ���,��ʱ֪ͨ�����߳�,�������ڴ��Ѿ����޸�
    private static void seeOkByVolatile() {
        Mydata mydata = new Mydata();//��Դ��

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " \t come in");
            //��ͣһ���߳�
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + " \t update number value :" + mydata.number);
        }, "AAA").start();

        //�ڶ����߳̾���main�߳�
        while (mydata.number == 0) {
            //main�߳̾�������һֱ�ȴ�ѭ��,ֱ��number��Ϊ��
        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over , " +
                "main get number value: " + mydata.number);
    }
}
