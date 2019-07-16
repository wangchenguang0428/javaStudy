package day01.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class TestMethodRef {


    //数组引用
    @Test
    public void test7() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);
        Function<Integer, String[]> function1 = String[]::new;
        String[] strs2 = function1.apply(20);
        System.out.println(strs2.length);

    }

    //构造器引用
    @Test
    public void test6() {
        Function<Integer, Employee> function = (x) -> new Employee(x);

        Function<Integer, Employee> function1 = Employee::new;
        Employee employee = function1.apply(101);
        System.out.println(employee);

        BiFunction<Integer, Integer, Employee> biFunction = Employee::new;


    }


    //构造器引用
    @Test
    public void test5() {
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();

        //构造器引用的方式
        Supplier<Employee> supplier1 = Employee::new;
        Employee employee1 = supplier1.get();
        System.out.println(employee1);


    }


    //类::实例方法名
    public void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate1 = String::equals;

    }

    //类::静态方法名
    @Test
    public void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        Comparator<Integer> comparator1 = Integer::compareTo;

    }


    @Test
    public void test1() {
        Consumer<String> consumer = (x) -> System.out.println(x);

        PrintStream ps = System.out;
        Consumer<String> consumer1 = ps::println;//函数式接口中的抽象方法和返回值类型 必须和引用方法中的参数和返回值类型保持一致

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
