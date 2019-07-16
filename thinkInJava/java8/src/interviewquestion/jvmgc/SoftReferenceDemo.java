package interviewquestion.jvmgc;

import java.lang.ref.SoftReference;

/**
 * @author wcg
 * @CreateDate 2019/7/11-18:04
 */
public class SoftReferenceDemo {


    /**
     * �ڴ湻�õ�ʱ��ͱ���,�����þͻ���
     */
    public static void softRef_Memory_Enough() {

        Object obj1 = new Object();//���������Ĭ�Ͼ���ǿ����
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(softReference.get());


    }

    /**
     *jvm����,����������������С���ڴ�,�����ڴ治���õ���OOM,�������õĻ������
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object obj1 = new Object();//���������Ĭ�Ͼ���ǿ����
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        try{
            byte [] bys = new byte[30*1024*1024];
        }catch (Exception e){
              e.printStackTrace();
        }finally {
            System.out.println("================================");
            System.out.println(obj1);
            System.out.println(softReference.get());
        }


    }

    public static void main(String[] args) {
//        softRef_Memory_Enough();
        softRef_Memory_NotEnough();

    }
}
