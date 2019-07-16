package day02.java8;

import java.io.Serializable;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @author wcg
 * @CreateDate 2019/6/30-16:42
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public  ForkJoinCalculate(long start,long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {

        long length = end-start;
        if(length<=THRESHOLD) {
            long sum = 0;
            for(long i=start;i<=end;i++) {
                sum +=i;
            }
            return sum;
        }else {
            long mid = (start+end)/2;
            ForkJoinCalculate left = new ForkJoinCalculate(start,mid);
            left.fork();//拆分子任务 同时压入线程队列
            ForkJoinCalculate right = new ForkJoinCalculate(mid+1,end);
            right.fork();
            return left.join()+right.join();

        }

    }
}
