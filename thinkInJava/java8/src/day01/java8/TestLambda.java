package day01.java8;

import org.junit.Test;

import java.util.*;

/**
 * @author wcg
 * @CreateDate 2019/6/22-17:03
 */
public class TestLambda {

    //原来的匿名内部类
    @Test
    public void test01() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //Lambda表达式
    @Test
    public void test02() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    List<Employee> employees = Arrays
            .asList(
                    new Employee("zhangsan", 18, 9999.99),
                    new Employee("lisi", 38, 5555.55),
                    new Employee("wangwu", 50, 6666.66),
                    new Employee("zhaoliu", 16, 3333.33),
                    new Employee("tianqi", 8, 7777.77)
            );

    @Test
    public void test03(){
        List<Employee> employees = filterEmployees(this.employees);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    //需求:获取当前公司中员工年龄大于35岁的员工信息
    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }
    //需求:获取当前公司员工工资大于5000的员工
   public List<Employee> filterEmployees2(List<Employee> list) {
       List<Employee> emps = new ArrayList<>();
       for (Employee emp : employees) {
           if (emp.getSalary() >= 5555) {
               emps.add(emp);
           }
       }
       return emps;
   }

   //优化方式一
    @Test
    public void test04(){
        List<Employee> list = filterEmployee(employees,new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

//    MyPredicate<Employee> filterBySalary = (x)-> {return x.getSalary()>=5555;} ;
    @Test
    public void test05(){
        List<Employee> list = filterEmployee(employees,new FilterEmployeeBySalary());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public  List<Employee> filterEmployee(List<Employee> list,
                                          MyPredicate<Employee> myPredicate) {
        List<Employee> emps = new ArrayList<>();

        for(Employee employee:employees) {
            if(myPredicate.test(employee)) {
                emps.add(employee);
            }
        }
        return emps;

    }

    //优化方式二
    @Test
    public void test06() {
        List<Employee> emps =  filterEmployee(employees, employee -> employee.getSalary()<=5555);
        emps.forEach(System.out::println);
    }

    //优化方式4 StreamAPI
    @Test
    public void test07() {
        employees.stream()
                .filter((e)->e.getSalary()>=5000)
                .forEach(System.out::println);

        System.out.println("---------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

    }


}
