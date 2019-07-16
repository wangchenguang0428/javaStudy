package day01.java8;

/**
 * @author wcg
 * @CreateDate 2019/6/22-17:49
 */
@FunctionalInterface
public interface MyPredicate <T> {


    public  boolean test(T t);

}
