package day01.java8;

/**
 * @author wcg
 * @CreateDate 2019/6/22-17:50
        */
public class FilterEmployeeByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge()>=35;
    }
}
