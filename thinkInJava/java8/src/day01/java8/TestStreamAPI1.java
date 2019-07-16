package day01.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/*
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 */
public class TestStreamAPI1 {

    //创建Stream
    @Test
    public void test1() {
        //1.可以通过Collection系列集合提供的stream()[串型流]或parallelStream[并行流]
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.通过Arrays中的静态方法stream获取
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3.通过Stream中的静态方法of()
       Stream<String> stream3 =  Stream.of("aa","bb","cc");

       //4.创建无限流
       //迭代
       Stream<Integer> stream4 = Stream.iterate(0,(x)->x+2);
       stream4.limit(10).forEach(System.out::println);

       //生成
        Stream.generate(()->new Random().nextDouble()).limit(5).forEach(System.out::println);

    }
}
