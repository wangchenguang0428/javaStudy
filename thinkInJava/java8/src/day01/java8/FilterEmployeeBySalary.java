package day01.java8;

/**
 * @author wcg
 * @CreateDate 2019/6/22-18:27
        */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary()>=5555;
    }
}
