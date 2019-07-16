package day01.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/*
 *  ��ֹ����
 */
public class TestStreamAPI3 {


    List<Employee> employees = Arrays.asList(
            new Employee(102, "����", 59, 6666.66, Status.FREE),
            new Employee(101, "����", 18, 9999.99, Status.FREE),
            new Employee(103, "����", 28, 3333.33, Status.VOCATION),
            new Employee(104, "����", 8, 7777.77, Status.BUSY),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(105, "����", 38, 5555.55, Status.BUSY)
    );


    /*
     * 	collect��������ת��Ϊ������ʽ������һ�� Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
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


    //����
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = employees.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 8000));

        System.out.println(map);
    }

    //�༶����
    @Test
    public void test7() {
        Map<Status, Map<String, List<Employee>>> map = employees.stream().collect(Collectors
                .groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (((Employee) e).getAge() <= 35) {
                        return "����";
                    } else if (((Employee) e).getAge() <= 50) {
                        return "����";
                    } else {
                        return "����";
                    }
                })));

        System.out.println(map);

    }


    //����
    @Test
    public void test6() {
        Map<Status, List<Employee>> map = employees.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));

        System.out.println(map);//mapû��foreach

    }

    @Test
    public void test5() {
        //����
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count);

        System.out.println("------------------");
        //ƽ��ֵ
        Double avg = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);
        //�ܺ�
        Double sum = employees.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //���ֵ
        Optional<Employee> optional1 = employees.stream()
                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(optional1.get());

        //��Сֵ
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
		��Լ
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) �������Խ�����Ԫ�ط�������������õ�һ��ֵ��
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


    //3. ��ֹ����
	/*
	    ������ƥ��
		allMatch��������Ƿ�ƥ������Ԫ��
		anyMatch��������Ƿ�����ƥ��һ��Ԫ��
		noneMatch��������Ƿ�û��ƥ���Ԫ��
		findFirst�������ص�һ��Ԫ��
		findAny�������ص�ǰ���е�����Ԫ��
		count������������Ԫ�ص��ܸ���
		max���������������ֵ
		min��������������Сֵ
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
