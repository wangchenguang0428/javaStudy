package day01.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

/*
 * һ��Lambda ���ʽ�Ļ����﷨��Java8��������һ���µĲ����� "->" �ò�������Ϊ��ͷ�������� Lambda ������
 * 						    ��ͷ�������� Lambda ���ʽ��ֳ������֣�
 *
 * ��ࣺLambda ���ʽ�Ĳ����б�
 * �ҲࣺLambda ���ʽ������ִ�еĹ��ܣ� �� Lambda ��
 *
 * �﷨��ʽһ���޲������޷���ֵ
 * 		() -> System.out.println("Hello Lambda!");
 *
 * �﷨��ʽ������һ�������������޷���ֵ
 * 		(x) -> System.out.println(x)
 *
 * �﷨��ʽ������ֻ��һ��������С���ſ���ʡ�Բ�д
 * 		x -> System.out.println(x)
 *
 * �﷨��ʽ�ģ����������ϵĲ������з���ֵ������ Lambda �����ж������
 *		Comparator<Integer> com = (x, y) -> {
 *			System.out.println("����ʽ�ӿ�");
 *			return Integer.compare(x, y);
 *		};
 *
 * �﷨��ʽ�壺�� Lambda ����ֻ��һ����䣬 return �� �����Ŷ�����ʡ�Բ�д
 * 		Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * �﷨��ʽ����Lambda ���ʽ�Ĳ����б���������Ϳ���ʡ�Բ�д����ΪJVM������ͨ���������ƶϳ����������ͣ����������ƶϡ�
 * 		(Integer x, Integer y) -> Integer.compare(x, y);
 *
 * ������������һ����ʡ
 * ����������ƶ�����ʡ
 * ��������ʡ��ʡ
 *
 * ����Lambda ���ʽ��Ҫ������ʽ�ӿڡ���֧��
 * ����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ� ����ʹ��ע�� @FunctionalInterface ����
 * 			 ���Լ���Ƿ��Ǻ���ʽ�ӿ�
 */
public class TestLambda2 {

    @Test
    public void test1() {
        int num = 0; //jdk1.7ǰ,������final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world!" +num);
            }
        };
        r.run();

        System.out.println("----------------------------------");

        Runnable r1 = ()-> System.out.println("hello Lambda!");
        r1.run();



    }

    @Test
    public void test2() {
        Consumer<String> consumer = x-> System.out.println(x);
        consumer.accept("�Ҵ��й������");

    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("����ʽ���");
            return Integer.compare(x,y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) ->  Integer.compare(x,y);

    }

    public void test5() {
        String[] strs = {"aaa","bbb","ccc"};
        List<String> list = new ArrayList<>();
        show(new HashMap<>());
    }

    public void show(Map<String,Integer> map) {

    }
    //����:��һ������������
    @Test
    public void test6 () {
       Integer num =  operate(100,(x) -> x-10);
       System.out.println(num);
        System.out.println(operate(200,(x)->x+300));
    }

    public Integer operate (Integer num, MyFunc func) {
        return func.getValue(num);


    }

}
