package day02.exer;

import day01.java8.Employee;
import day01.java8.Status;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author wcg
 * @CreateDate 2019/6/30-14:43
 */
public class TestStreamAPI {



    List<Employee> employees = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.FREE),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );
    /**
     * 1. 给定一个数字的列表,如何返回一个由每个数的平方构成的列表呢?
     * 例如,给定[1,2,3,4,5] 返回[1,4,9,16,25]
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums).map((x)->x*x).forEach(System.out::println);

    }

    /**
     * 2. 怎样用map和reduce方法数一数流中有多少个Employee呢?
     */
    @Test
    public void test2() {
        Optional<Integer> count = employees.stream()
                .map((e) -> 1).reduce(Integer::sum);
        System.out.println(count.get());

    }





}
