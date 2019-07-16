package interviewquestion.consumerproducer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {

    private volatile boolean flag = true;//Ĭ�Ͽ��� ,��������������
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
                System.out.println(Thread.currentThread().getName() + "\t �������" + data + "�ɹ�");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t �������" + data + "ʧ��");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "\t ���ϰ��ͣ��,��ʾflag =false,������������");
    }

    public void myConsumer () throws Exception {
        String result = null;
        while(flag) {
            result =  blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (result == null || "".equalsIgnoreCase(result)) {
//                flag =false;
                System.out.println(Thread.currentThread().getName() + "\t ����������û��ȡ������,�����˳�");
                System.out.println();
                System.out.println();
                System.out.println();
                return;
            }

            System.out.println(Thread.currentThread().getName() + "\t ���Ѷ���" + result + "�ɹ�");
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
 * volatile/CAS/atomicInteger/BlockQueue/�߳̽���
 */
public class ProdCosumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception {

        MyResource myResource = new MyResource(new LinkedBlockingQueue<>(12));



        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t �����߳�����!");
            System.out.println();
            System.out.println();
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }

        },"product").start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + "\t �����߳�����!");
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

        //��ͣһ���߳�
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5��ʱ�䵽,���ϰ�main�߳̽�ͣ, �����");

        myResource.stop();

    }
}
