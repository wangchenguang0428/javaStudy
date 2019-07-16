package day01.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/*
 * һ���������ã��� Lambda ���еĹ��ܣ��Ѿ��з����ṩ��ʵ�֣�����ʹ�÷�������
 * 			  �����Խ������������Ϊ Lambda ���ʽ������һ�ֱ�����ʽ��
 *
 * 1. ��������� :: ʵ��������
 *
 * 2. ���� :: ��̬������
 *
 * 3. ���� :: ʵ��������
 *
 * ע�⣺
 * 	 �ٷ������������õķ����Ĳ����б��뷵��ֵ���ͣ���Ҫ�뺯��ʽ�ӿ��г��󷽷��Ĳ����б�ͷ���ֵ���ͱ���һ�£�
 * 	 ����Lambda �Ĳ����б�ĵ�һ����������ʵ�������ĵ����ߣ��ڶ�������(���޲�)��ʵ�������Ĳ���ʱ����ʽ�� ClassName::MethodName
 *
 * �������������� :�������Ĳ����б���Ҫ�뺯��ʽ�ӿ��в����б���һ�£�
 *
 * 1. ���� :: new
 *
 * ������������
 *
 * 	����[] :: new;
 *
 *
 */
public class TestMethodRef {


    //��������
    @Test
    public void test7() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);
        Function<Integer, String[]> function1 = String[]::new;
        String[] strs2 = function1.apply(20);
        System.out.println(strs2.length);

    }

    //����������
    @Test
    public void test6() {
        Function<Integer, Employee> function = (x) -> new Employee(x);

        Function<Integer, Employee> function1 = Employee::new;
        Employee employee = function1.apply(101);
        System.out.println(employee);

        BiFunction<Integer, Integer, Employee> biFunction = Employee::new;


    }


    //����������
    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();

        //���������õķ�ʽ
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier1.get();
        System.out.println(employee1);


    }


    //��::ʵ��������
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;

    }

    //��::��̬������
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator1 = Integer::compareTo;

    }


    @Test
    public void test1() {
        Consumer<String> consumer = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;//����ʽ�ӿ��еĳ��󷽷��ͷ���ֵ���� ��������÷����еĲ����ͷ���ֵ���ͱ���һ��

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("aaaaaa");
    }

    public void test2() {
        Employee employee = new Employee();
        Supplier<String> supplier = () -> employee.getName();
        String string = supplier.get();
        System.out.println(string);
        Supplier<Integer> supplier1 = employee::getAge;
        Integer num = supplier1.get();
        System.out.println(num);
    }


}
