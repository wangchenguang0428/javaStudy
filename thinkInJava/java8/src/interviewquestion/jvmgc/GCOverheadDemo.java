package interviewquestion.jvmgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcg
 * @CreateDate 2019/7/12-11:57
 *
 * jvm������ʾ
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC����ʱ��������׳�OutofMemoryError�������Ķ�����,����98%��ʱ��������GC���һ��յ��˲���2%�Ķ��ڴ�
 * �������GC��ֻ�����˲���2%�ļ�������²Ż��׳������粻�׳�GC overhead limit ����ᷢ��ʲô�����?
 * �Ǿ���GC�������ô���ڴ�ܿ��ֻᱻ����,��ʹGC�ٴ�ִ��,�������γɶ���ѭ��
 * CPUʹ����һһֱ��100%����GCȴû���κγɹ�
 *
 *
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i = 0;

        List<String> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }

        } catch (Throwable e) {
            System.out.println("*********************i:" + i);
            e.printStackTrace();//java.lang.OutOfMemoryError: GC overhead limit exceeded
            throw e;
        }


    }


}
