package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/11-17:58
 * 只要有一个引用指向该对象 就算是爆发OOM它也不会被回收
 *
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();//这样定义的默认就是强引用
        Object obj2 = obj1;
        obj1 = null;//置空
        System.gc();
        System.out.println(obj2);
    }

}
