package day01.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/*
 * һ�� Stream �Ĳ�������
 *
 * 1. ���� Stream
 *
 * 2. �м����
 *
 * 3. ��ֹ����
 */
public class TestStreamAPI2 {
    List<Employee> emps = Arrays.asList(
            new Employee(102, "����", 59, 6666.66),
            new Employee(101, "����", 18, 9999.99),
            new Employee(103, "����", 28, 3333.33),
            new Employee(104, "����", 8, 7777.77),
            new Employee(104, "����", 8, 7777.77),
            new Employee(104, "����", 8, 7777.77),
            new Employee(105, "����", 38, 5555.55)
    );

    /*
		sorted()������Ȼ����(Comparable)
		sorted(Comparator com)������������
	 */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("-------------------");
        emps.stream().sorted((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        }).forEach(System.out::println);

    }


    /*
		ӳ��
		map�������� Lambda �� ��Ԫ��ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
		flatMap��������һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ����
	 */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("-------------------------");
        emps.stream().map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("-------------------------");

        Stream<Stream<Character>> chStream = list.stream()
                .map(TestStreamAPI2::filterCharacter);

        chStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("----------------------------");

        list.stream().flatMap(TestStreamAPI2::filterCharacter)
                .forEach(System.out::println);

    }


    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        char[] chs = str.toCharArray();
        for (Character ch : chs) {
            list.add(ch);
        }
        return list.stream();

    }




    /*
        ɸѡ����Ƭ
        filter:����lambda,�������ų�ĳЩԪ��
        limit:�ض���,ʹ��Ԫ�ز�����������������
        skip(n):����Ԫ��,����һ���ӵ���n��Ԫ��,�������в���n��Ԫ��,�򷵻�һ������,��limit(n)����
        distinct:ɸѡ,ͨ��������Ԫ�ص�hashCode()��equalsȥ���ظ�Ԫ��

     */

    @Test
    public void test4() {
        emps.stream().filter((e) -> e.getSalary() > 5000)
                .skip(3).distinct().forEach(System.out::println);

    }


    @Test
    public void test3() {
        emps.stream().filter((e) -> {
            System.out.println("��·");
            return e.getSalary() > 5000;
        }).limit(2).forEach(System.out::println);
    }

    //�ڲ�����:����������StreamAPI���
    @Test
    public void test1() {
        //�м����:����ִ���κβ���
        Stream<Employee> s =
                emps.stream().filter((e) -> {
                    System.out.println("StreamAPI���м����");
                    return e.getAge() > 35;
                });

        //��ֹ����:һ����ִ��ȫ������,��"������ֵ"
        s.forEach(System.out::println);

    }

    //�ⲿ����
    @Test
    public void test2() {
        Iterator<Employee> it = emps.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }

}



