package com.jvm.base001;

/**
 * @author wcg
 * @CreateDate 2018/12/25-20:09
 */
public class Test007 {

    public static void alloc(){
        byte[] b = new byte[2];
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        //TLAB∑÷≈‰
        //≤Œ ˝:-XX:+UseTLAB -XX:+PrintTLAB -XX:+PrintGC -XX:TLABSize=102400 -XX:-ResizeTLAB -XX:TLABRefillWasteFraction=100 -XX:DoEscapeAnalysis -server
        for(int i=0;i<10000000;i++){
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}
