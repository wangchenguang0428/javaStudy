package com.atguigu.juc03;

import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/6/17-18:32
 */
public class Profiler {
    // ��һ��get()��������ʱ����г�ʼ�������set����û�е��ã���ÿ���̻߳����һ��
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        protected Long initialValue() {
        return System.currentTimeMillis();
    }
};
    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }
    public static final long end() {
        System.out.println(TIME_THREADLOCAL.get());
        System.out.println(System.currentTimeMillis());
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }
    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
