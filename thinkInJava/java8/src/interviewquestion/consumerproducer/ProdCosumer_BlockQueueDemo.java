package interviewquestion.consumerproducer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {

    private volatile boolean flag = true;//默认开启 ,进行生产加消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;

    }

    public void myProd() throws Exception {
        String data = null;
        boolean retVal;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            retVal = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retVal) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了,表示flag =false,生产动作结束");
    }

    public void myConsumer () throws Exception {
        String result = null;
        while(flag) {
            result =  blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (result == null || "".equalsIgnoreCase(result)) {
//                flag =false;
                System.out.println(Thread.currentThread().getName() + "\t 超过两秒钟没有取到蛋糕,消费退出");
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }

    }

    public void stop() throws Exception{
        this.flag = false;
    }


}

/**
 * @author wcg
 * @CreateDate 2019/7/9-17:56
 * <p>
 * <p>
 * volatile/CAS/atomicInteger/BlockQueue/线程交互
 */
public class ProdCosumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception {

        MyResource myResource = new MyResource(new LinkedBlockingQueue<>(12));



        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动!");
            System.out.println();
            System.out.println();
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"product").start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动!");
            System.out.println();
            System.out.println();
            try {
                myResource.myConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"consumer").start();

        //暂停一会线程
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒时间到,大老板main线程叫停, 活动结束");

        myResource.stop();

    }
}
