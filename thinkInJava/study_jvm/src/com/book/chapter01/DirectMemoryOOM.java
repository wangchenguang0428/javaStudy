package com.book.chapter01;

/**
 * @author wcg
 * @CreateDate 2019/6/28-10:57
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args£º-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author zzm
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
            System.out.println(unsafeField.getClass());
            unsafeField.setAccessible(true);
            Unsafe unsafe = (Unsafe) unsafeField.get(null);
        System.out.println(unsafe);
int i= 0;
            while (true) {
                unsafe.allocateMemory(_1MB);
                System.out.println(i++);
        }
    }
}
