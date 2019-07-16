package day02.java8;

import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * 重复注解与接口注解
 */
public class TestAnnotation {


    //获取反射实例
    @Test
    public void test1() throws NoSuchMethodException {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");

        MyAnnotation[] annotationsByType = m1.getAnnotationsByType(MyAnnotation.class);

        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation);

        }


    }

    @MyAnnotation("hello")
    @MyAnnotation("world")
    public void show() {

    }
}
