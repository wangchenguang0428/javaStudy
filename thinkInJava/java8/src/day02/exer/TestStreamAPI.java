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
            new Employee(102, "����", 59, 6666.66, Status.FREE),
            new Employee(101, "����", 18, 9999.99, Status.FREE),
            new Employee(103, "����", 28, 3333.33, Status.VOCATION),
            new Employee(104, "����", 8, 7777.77, Status.BUSY),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(104, "����", 8, 7777.77, Status.FREE),
            new Employee(105, "����", 38, 5555.55, Status.BUSY)
    );
    /**
     * 1. ����һ�����ֵ��б�,��η���һ����ÿ������ƽ�����ɵ��б���?
     * ����,����[1,2,3,4,5] ����[1,4,9,16,25]
     */
    @Test
    public void test1(){
        Integer[] nums = new Integer[]{1,2,3,4,5};

        Arrays.stream(nums).map((x)->x*x).forEach(System.out::println);

    }

    /**
     * 2. ������map��reduce������һ�������ж��ٸ�Employee��?
     */
    @Test
    public void test2() {
        Optional<Integer> count = employees.stream()
                .map((e) -> 1).reduce(Integer::sum);
        System.out.println(count.get());

    }





}
