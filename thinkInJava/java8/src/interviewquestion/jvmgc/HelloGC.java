package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/11-14:03
 */
public class HelloGC {

    public static void main(String[] args) throws Exception {
        System.out.println("********helloGC");
//        Thread.sleep(Integer.MAX_VALUE);
        byte[] byteArray = new byte[50 * 1024 * 1024];

    }
}
