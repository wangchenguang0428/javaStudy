package day02.java8;

import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * �ظ�ע����ӿ�ע��
 */
public class TestAnnotation {


    //��ȡ����ʵ��
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
