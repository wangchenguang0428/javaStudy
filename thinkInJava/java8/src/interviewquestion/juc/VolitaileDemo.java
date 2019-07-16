package interviewquestion.juc;

import java.sql.Time;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wcg
 * @CreateDate 2019/7/3-14:46
 */


class Mydata { //MyData.java -> Mydata.class ->JVM字节码

    volatile int number = 0;

    public void addTo60() {
        this.number = 60;

    }

    //请注意,number此时前面是加了volatile关键字修饰的, volatile不保证原子性
    public  void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }



}

/**
 * 1.验证volatile的可见性
 * 1.1 假如int number = 0; number变量之前根本没有添加volitaile关键字修饰,没有可见性
 * 1.2 添加了volatile,可以将解决可见性问题
 * 2.验证volatile不保证原子性
 * 2.1 原子性指的是什么意思?
 * 不可分割,完整性, 也即某个线程在做具体业务的时候,中间不可以被加塞或者被分割.需要整体完整
 * 要么同时成功,要么同时失败
 * 2.2 是否可以保证原子性案例演示
 *
 * 2.3 why
 *
 * 2.4 如何解决原子性?
 *    加sychronized
 *    使用我们juc下的AtomicInteger
 */
public class VolitaileDemo {

    //原子性:就是最终一致性是不是能够得到保证,中间不要被加塞
    public static void main(String[] args) {//main只是一切方法的运行入口

        Mydata mydata = new Mydata();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    mydata.addPlusPlus();
                    mydata.addAtomic();
                }

            }, String.valueOf(i)).start();
        }
        //需要等待上面20个线程全部计算完成后,再用main线程取得最终的结果值看是多少
        while (Thread.activeCount() > 2) {//为什么是两个线程?因为后台有两个线程(一个是main线程,一个是gc线程)
            Thread.yield();

        }

        System.out.println(Thread.currentThread().getName()+" \t int type, finally number value:" + mydata.number);
        System.out.println(Thread.currentThread().getName()+" \t AtomicInteger type, finally number value:" + mydata.atomicInteger);

    }


    //volatile可以保证可见性,及时通知其他线程,主物理内存已经被修改
    private static void seeOkByVolatile() {
        Mydata mydata = new Mydata();//资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " \t come in");
            //暂停一会线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + " \t update number value :" + mydata.number);
        }, "AAA").start();

        //第二个线程就是main线程
        while (mydata.number == 0) {
            //main线程就在这里一直等待循环,直到number不为零
        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over , " +
                "main get number value: " + mydata.number);
    }
}
