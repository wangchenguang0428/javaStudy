package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/12-11:12
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();

    }

    private static void stackOverflowError() {
        stackOverflowError();//Exception in thread "main" java.lang.StackOverflowError
    }
}
