package day01.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java8 ���õ��Ĵ���ĺ���ʽ�ӿ�
 *
 * Consumer<T> : �����ͽӿ�
 * 		void accept(T t);
 *
 * Supplier<T> : �����ͽӿ�
 * 		T get();
 *
 * Function<T, R> : �����ͽӿ�
 * 		R apply(T t);
 *
 * Predicate<T> : �����ͽӿ�
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    //Predicate<T> �����ͽӿ�:

    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "atguigu", "lambda", "ww", "ok");
        List<String>  strs = filterStr(list, (str) -> {
            if (str.length() > 3) return true;
            else return false;
        });
        for (String s : strs) {
            System.out.println(s);
        }


    }

    //����:�������������ַ������뼯����
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;

    }


    //Function<T,R> �����ͽӿ�
    @Test
    public void test3() {
        String retStr = strHandler("\t\t\t �Ҵ��й������ ",
                (str) -> str.trim());
        System.out.println(retStr);
    }

    //����:���ڴ����ַ���
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }


    //supplier<T>�����ͽӿ�:
    @Test
    public void test2() {
        List list = getNumList(10, () -> new Random().nextInt(100));
        for (Object o : list) {
            System.out.println(o);

        }
    }

    //����һЩ����,�����뼯����
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;

    }


    //Consumer<T>�����ͽӿ�:
    @Test
    public void test1() {
        happy(1000, (m) -> System.out.println("���Ǹո�ϲ���󱣽� ÿ�����ѣ�" + m + "Ԫ"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

}
