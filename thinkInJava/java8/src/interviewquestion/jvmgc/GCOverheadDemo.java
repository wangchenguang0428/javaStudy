package interviewquestion.jvmgc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcg
 * @CreateDate 2019/7/12-11:57
 *
 * jvm参数演示
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC回收时间过长会抛出OutofMemoryError。过长的定义是,超过98%的时间用来做GC并且回收到了不到2%的堆内存
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。假如不抛出GC overhead limit 错误会发生什么情况呢?
 * 那就是GC清理的那么点内存很快又会被填满,迫使GC再次执行,这样就形成恶性循环
 * CPU使用率一一直是100%，而GC却没有任何成果
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
