package com.jvm.base001;

import java.util.Vector;

/**
 * @author wcg
 * @CreateDate 2018/12/24-16:45
 */
public class Test003 {

    public static void main(String[] args) {
        //-Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/test03.dump
        //¶ÑÄÚ´æÒç³ö
        Vector v = new Vector();
        for(int i=0; i<5; i++){
            v.add(new Byte[1*1024*1024]);
        }


    }
}
