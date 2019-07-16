package interviewquestion.jvmgc;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author wcg
 * @CreateDate 2019/7/12-17:16
 *
 * 1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC     (DefNew+Tenured)
 * 2
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC      (ParNew+Tenured)
 *
 *
 * ��ע���: java HotSpot(TM) 64-Bit Server VM warning
 * Using the ParNew young collector with the serial old collector is deprecated
 * and will likely be removed in a future release
 *
 * 3
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC   (PsYoungGen+ParOldGen)
 *
 * 4
 * 4.1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC   (PsYoungGen+ParOldGen)
 *4.2 ���Ӿ���Ĭ��UseParallelGC
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags                       (PsYoungGen+ParOldGen)
 *
 * 5.
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseConcMarkSweepGC                      (par new generation+concurrent mark sweep)
 *
 * 6.
 *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseG1GC                     ���浥����G1
 *
 * 7.(����֪������,ʵ����java8�Ѿ����Ż�����,û���ˡ�)
 *   -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags  -XX:+UseSerialOldGC                   ���浥����G1
 *
 */
public class HelloGC02 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello2");
//        byte[] bs = new byte[30*1024*1024];
//        Thread.sleep(Integer.MAX_VALUE);
        String str = "datang";
        List<String> list = new LinkedList<>();

        while(true) {
            str+=str + new Random().nextInt(11111)+new Random().nextInt(22222);
            list.add(str);
        }


    }
}
