package com.jvm.base001;

/**
 * @author wcg
 * @CreateDate 2018/12/24-17:36
 */
public class Test004 {

    //-Xss1m
    //-Xss5m


    //ջ�������
    private static int count;

    public static void recursion() {
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Throwable r) {
            System.out.println("�����������:" + count);
            r.printStackTrace();
        }
    }


}
