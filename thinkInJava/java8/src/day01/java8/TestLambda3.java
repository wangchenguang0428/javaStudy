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
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    //Predicate<T> 断言型接口:

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

    //需求:将满足条件的字符串放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;

    }


    //Function<T,R> 函数型接口
    @Test
    public void test3() {
        String retStr = strHandler("\t\t\t 我大尚硅谷威武 ",
                (str) -> str.trim());
        System.out.println(retStr);
    }

    //需求:用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }


    //supplier<T>供给型接口:
    @Test
    public void test2() {
        List list = getNumList(10, () -> new Random().nextInt(100));
        for (Object o : list) {
            System.out.println(o);

        }
    }

    //产生一些整数,并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }
        return list;

    }


    //Consumer<T>消费型接口:
    @Test
    public void test1() {
        happy(1000, (m) -> System.out.println("你们刚哥喜欢大保健 每次消费：" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

}
