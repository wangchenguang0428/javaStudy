package interviewquestion.jvmgc;

import java.lang.ref.SoftReference;

/**
 * @author wcg
 * @CreateDate 2019/7/11-18:04
 */
public class SoftReferenceDemo {


    /**
     * 内存够用的时候就保留,不够用就回收
     */
    public static void softRef_Memory_Enough() {

        Object obj1 = new Object();//这样定义的默认就是强引用
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(softReference.get());


    }

    /**
     *jvm配置,故意产生大对象并配置小的内存,让他内存不够用导致OOM,看软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough() {
        Object obj1 = new Object();//这样定义的默认就是强引用
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
