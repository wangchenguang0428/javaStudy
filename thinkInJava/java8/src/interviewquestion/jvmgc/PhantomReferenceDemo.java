package interviewquestion.jvmgc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author wcg
 * @CreateDate 2019/7/12-10:36
 * java�ṩ��4����������,���������յ�ʱ��,�����Լ����ص�
 * referenceQueue������������ù�����,û��referenceQueueһ����������
 *
 * �������õ�ʱ�����ָ�������Ķ���,��GC�ͷ������ڴ��ʱ��,�Ὣ���ü��뵽���ö���,
 * ���������ĳ���������Ѿ����뵽���ö���,��ô�Ϳ����������õĶ����ڻ���֮ǰ��ȡ��Ҫ���ж�
 *���൱��һ��֪ͨ����
 *
 * �����������ö����������ݵ�ʱ��,��ζ������ָ��Ķ��ڴ��еĶ��󱻻��ա�ͨ�����ַ�ʽ,JVM���������ڶ������ٺ�
 * ��һЩ�����Լ�����������
 *
 *
 *
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("==================================");
        o1=null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());





    }


}
