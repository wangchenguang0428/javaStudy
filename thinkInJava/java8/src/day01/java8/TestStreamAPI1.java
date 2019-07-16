package day01.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/*
 * һ��Stream API �Ĳ������裺
 *
 * 1. ���� Stream
 *
 * 2. �м����
 *
 * 3. ��ֹ����(�ն˲���)
 */
public class TestStreamAPI1 {

    //����Stream
    @Test
    public void test1() {
        //1.����ͨ��Collectionϵ�м����ṩ��stream()[������]��parallelStream[������]
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2.ͨ��Arrays�еľ�̬����stream��ȡ
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3.ͨ��Stream�еľ�̬����of()
       Stream<String> stream3 =  Stream.of("aa","bb","cc");

       //4.����������
       //����
       Stream<Integer> stream4 = Stream.iterate(0,(x)->x+2);
       stream4.limit(10).forEach(System.out::println);

       //����
        Stream.generate(()->new Random().nextDouble()).limit(5).forEach(System.out::println);

    }
}
