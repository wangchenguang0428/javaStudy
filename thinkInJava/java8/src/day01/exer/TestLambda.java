package day01.exer;


import day01.java8.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

/**
 * @author wcg
 * @CreateDate 2019/6/23-11:40
 */
public class TestLambda {

    BinaryOperator<Double> bo = (x,y)->Math.pow(x,y);

    List<Employee> employees = Arrays
            .asList(
                    new Employee("zhangsan", 18, 9999.99),
                    new Employee("lisi", 38, 5555.55),
                    new Employee("wangwu", 50, 6666.66),
                    new Employee("zhaoliu", 16, 3333.33),
                    new Employee("tianqi", 8, 7777.77)
            );


    @Test
    public void test111() {
        System.out.println();

    }


    @Test
    public void test1 () {
        Collections.sort(employees,(e1,e2) ->{
            if (e1.getAge()==e2.getAge() ){
                return e1.getName().compareTo(e2.getName());
            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
            }
        } );

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Test
    public void test2(){
       String trimStr =  strHandler(" \t\t\t我大尚硅谷威武 ", (str)-> str.trim());
        System.out.println(trimStr);

        System.out.println(strHandler("abcdef",(str)->str.toUpperCase()));
    }

    @Test
    public void test3(){
        op(100L,200L,(x,y)->x+y);
    }



    //需求:用于处理字符串
    public String strHandler (String str, MyFunction mf){
        return mf.getValue(str);
    }

    //需求:对于两个Long型数据进行处理
    public void op(Long l1, Long l2, MyFunction2<Long,Long> mf){
        System.out.println(mf.getValue(l1,l2));
    }

}
