package interviewquestion.jvmgc;

import java.util.Random;

/**
 * @author wcg
 * @CreateDate 2019/7/12-18:15
 */
public class GCDemo {

    public static void main(String[] args) {
        System.out.println("*************GCDemo hello");
        try{

            String str = "atdatang";
            while(true) {
                str+=str+ new Random().nextInt(11111)+new Random().nextInt(222222);
                str.intern();
            }

        }catch (Exception e){
              e.printStackTrace();
        }finally {

        }
    }
}
