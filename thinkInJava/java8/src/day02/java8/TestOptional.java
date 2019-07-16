package day02.java8;

import org.junit.Test;

import java.util.EnumMap;
import java.util.Optional;

/*
        Optional����null���ж� ����װ������������
 * һ��Optional �����ࣺ���ھ��������ָ���쳣
 * 	Optional.of(T t) : ����һ�� Optional ʵ��
 * 	Optional.empty() : ����һ���յ� Optional ʵ��
 * 	Optional.ofNullable(T t):�� t ��Ϊ null,���� Optional ʵ��,���򴴽���ʵ��
 * 	isPresent() : �ж��Ƿ����ֵ
 * 	orElse(T t) :  ������ö������ֵ�����ظ�ֵ�����򷵻�t
 * 	orElseGet(Supplier s) :������ö������ֵ�����ظ�ֵ�����򷵻� s ��ȡ��ֵ
 * 	map(Function f): �����ֵ���䴦�������ش�����Optional�����򷵻� Optional.empty()
 * 	flatMap(Function mapper):�� map ���ƣ�Ҫ�󷵻�ֵ������Optional
 */
public class TestOptional {


    @Test
    public void test5(){
        Man man = new Man();

        String name = getGodnessName(man);
        System.out.println(name);
    }

    //���󣺻�ȡһ����������Ů�������
    public String getGodnessName(Man man){
        if(man != null){
            Godness g = man.getGod();

            if(g != null){
                return g.getName();
            }
        }

        return "����ʦ";
    }

    //���� Optional ��ʵ����
    @Test
    public void test6(){
        Optional<Godness> godness = Optional.ofNullable(null);

        Optional<NewMan> op = Optional.ofNullable(new NewMan(godness));
        String name = getGodnessName2(op);
        System.out.println(name);
    }

    public String getGodnessName2(Optional<NewMan> man){
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("����ʦ"))
                .getName();
    }


    @Test
    public void test4(){
        Optional<Employee> op = Optional.of(new Employee(101, "����", 18, 9999.99));

        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());

        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }


    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(null);

        if (op.isPresent()) {
            System.out.println(op.get());
        }
        Employee emp = op.orElse(new Employee(101, "����", 9999, 999));
        System.out.println(emp);
        Employee emp2 = op.orElseGet(() -> new Employee());
        System.out.println(emp2);
    }

    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    @Test
    public void test1() {

        Optional<Employee> op = Optional.of(null);

        Employee emp = op.get();

        System.out.println(op.get());

    }


}
