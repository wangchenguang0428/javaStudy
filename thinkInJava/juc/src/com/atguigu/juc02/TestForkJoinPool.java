package com.atguigu.juc02;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author wcg
 * @CreateDate 2019/6/15-19:36
 */
public class TestForkJoinPool {

    public static void main(String[] args) {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 10000000000L);

        Long sum = pool.invoke(task);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());//166-1996-10590


    }


    @Test
    public void test01(){
        Instant start = Instant.now();

        long sum = 0L;

        for (long i = 0L; i <= 10000000000L; i++) {
            sum += i;
        }

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());//35-3142-15704
    }

    //java8 ������
    @Test
    public void test2(){
        Instant start = Instant.now();

        Long sum = LongStream.rangeClosed(0L, 10000000000L)
                .parallel()
                .reduce(0L, Long::sum);

        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("�ķ�ʱ��Ϊ��" + Duration.between(start, end).toMillis());//1536-8118
    }

}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    /**
     *
     */
    private static final long serialVersionUID = -259195479995561737L;

    private long start;
    private long end;

    private static final long THURSHOLD = 10000L;  //�ٽ�ֵ

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THURSHOLD){
            long sum = 0L;

            for (long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }else{
            long middle = (start + end) / 2;

            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork(); //���в�֣�ͬʱѹ���̶߳���

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle+1, end);
            right.fork(); //

            return left.join() + right.join();
        }
    }
}