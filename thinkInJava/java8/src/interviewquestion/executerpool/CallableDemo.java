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
 * 多线程中 第三种获得线程的方式
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        //两个线程,一个是main线程, 一个是AAFutureTask

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

        int result02 = futureTask.get();//要求获得Callable线程的计算结果,如果没有计算完成就要去强求,会导致堵塞,直到计算完成
        System.out.println("******result: " + (result01 + result02));

    }
}
