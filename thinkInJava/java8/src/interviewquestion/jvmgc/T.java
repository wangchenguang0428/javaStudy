package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/12-14:45
 */
public class T {

    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();



    }

}
