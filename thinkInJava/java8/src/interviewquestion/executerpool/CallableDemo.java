package interviewquestion.executerpool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName()+"************come in Callable");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

/**
 * @author wcg
 * @CreateDate 2019/7/10-10:39
 * ���߳��� �����ֻ���̵߳ķ�ʽ
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //�����߳�,һ����main�߳�, һ����AAFutureTask

        String str = "";
        // public FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();
        System.out.println();


        System.out.println(Thread.currentThread().getName() + "******************");
        int result01 = 100;

//        while(!futureTask.isDone()) {
//
//        }

        int result02 = futureTask.get();//Ҫ����Callable�̵߳ļ�����,���û�м�����ɾ�Ҫȥǿ��,�ᵼ�¶���,ֱ���������
        System.out.println("******result: " + (result01 + result02));

    }
}
