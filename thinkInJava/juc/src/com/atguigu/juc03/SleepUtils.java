package com.atguigu.juc03;

import java.util.concurrent.TimeUnit;

/**
 * @author wcg
 * @CreateDate 2019/6/17-16:40
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}