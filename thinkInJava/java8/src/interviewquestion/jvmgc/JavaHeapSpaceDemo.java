package interviewquestion.jvmgc;

import java.util.Random;

/**
 * @author wcg
 * @CreateDate 2019/7/12-11:35
 */
public class JavaHeapSpaceDemo {


    public static void main(String[] args) {
        String str = "atguigu";
        while(true) {
            str+= str + new Random().nextInt(1111111) + new Random().nextInt(2222222);
            str.intern();//Exception in thread "main" java.lang.OutOfMemoryError: Java heap space

        }
    }
}
