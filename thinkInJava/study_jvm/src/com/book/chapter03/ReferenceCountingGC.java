package com.book.chapter03;

import org.junit.Test;

/**
 * testGC()����ִ�к�objA��objB�᲻�ᱻGC�أ�
 * @author zzm
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    /**
     * �����Ա���Ե�Ψһ�������ռ���ڴ棬�Ա�������GC��־�п�����Ƿ��л��չ�
     */
    private byte[] bigSize = new byte[2 * _1MB];

    @Test
    public  void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // ���������з���GC��objA��objB�Ƿ��ܱ����գ�
        System.gc();
    }
}