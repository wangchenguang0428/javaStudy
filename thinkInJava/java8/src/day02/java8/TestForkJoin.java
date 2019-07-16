package day02.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author wcg
 * @CreateDate 2019/6/30-17:44
 */
public class TestForkJoin {


    /**
     * ForkJoin框架
     */

    @Test
    public  void test1 () {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        RecursiveTask<Long> task = new ForkJoinCalculate(0,10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());

    }

    /**
     * 普通for
     */
    @Test
    public void test2 () {
        Instant start = Instant.now();

        long sum =0L;
        for (long i=0;i<10000000000L;i++) {
            sum +=i;
        }

        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }

    /**
     * java8并行流
     */
    @Test
    public void test3() {
        Instant start = Instant.now();
       Long sum = LongStream.rangeClosed(0,10000000000L).parallel().reduce(0,Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();

        System.out.println("耗费时间为:" + Duration.between(start,end).toMillis());
    }



}
