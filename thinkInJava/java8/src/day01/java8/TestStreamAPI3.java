package day01.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
 *  终止操作
 */
public class TestStreamAPI3 {


    List<Employee> employees = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.FREE),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );


    /*
     * 	collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     */


    @Test
    public void test10 () {
        String collect = employees.stream()
                .map(Employee::getName).collect(Collectors.joining(",","=====",""));
        System.out.println(collect);
    }

    @Test
    public void test9() {
        DoubleSummaryStatistics doubleSummaryStatistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getMax());
        System.out.println(doubleSummaryStatistics.getMin());
    }


    //分区
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));

        System.out.println(map);
    }

    //多级分组
    @Test
    public void test7() {
        Map<Status, Map<String, List<Employee>>> map = employees.stream().collect(Collectors
                .groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "青年";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "中年";
                    } else {
                        return "老年";
                    }
                })));

        System.out.println(map);

    }


    //分组
    @Test
    public void test6() {
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);//map没有foreach

    }

    @Test
    public void test5() {
        //总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);

        System.out.println("------------------");
        //平均值
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        //总和
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //最大值
        Optional<Employee> optional1 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(optional1.get());

        //最小值
        Optional<Double> optional2 = employees
                .stream().map(Employee::getSalary).collect(Collectors.minBy(Double::compareTo));
        System.out.println(optional2.get());


    }

    @Test
    public void test4() {

        List<String> list = employees.stream()
                .map(Employee::getName).collect(Collectors.toList());

        list.forEach(System.out::println);

        System.out.println("---------------------------");

        Set<String> set = employees.stream()
                .map(Employee::getName).collect(Collectors.toSet());

        set.forEach(System.out::println);

        System.out.println("---------------------------");

        HashSet<String> hs = employees.stream().map(Employee::getName)
                .collect(Collectors.toCollection(() -> new HashSet<>()));
        hs.forEach(System.out::println);


    }


    /*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        System.out.println("----------------------");

        Optional<Double> sumSal = employees.stream()
                .map(Employee::getSalary).reduce(Double::sum);
        System.out.println(sumSal.get());


    }


    //3. 终止操作
	/*
	    查找与匹配
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test2() {
        long count = employees.stream().count();
        System.out.println(count);

        Optional<Employee> optional1 = employees.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optional1.get());

        Optional<Double> optional2 = employees.stream()
                .map(Employee::getSalary).min(Double::compareTo);
        System.out.println(optional2.get());

    }


    @Test
    public void test1() {
        Boolean b1 = employees.stream()
                .allMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b1);


        Boolean b2 = employees.stream()
                .anyMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b2);

        Boolean b3 = employees.stream()
                .noneMatch((e) -> e.getStatus().equals(Status.BUSY));
        System.out.println(b3);

        Optional<Employee> optional1 = employees.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(optional1.get());

        Optional<Employee> optional2 = employees.parallelStream()
                .filter((e) -> e.getStatus().equals(Status.FREE)).findAny();
        System.out.println(optional2.get());
    }


}
