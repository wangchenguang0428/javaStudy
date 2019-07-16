package com.jvm.base001;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcg
 * @CreateDate 2018/12/25-17:43
 */
public class Test005 {

    public static void main(String[] args) {
        //初始对象在eden区
        //参数:-Xmx64m -Xms64m -XX:+PrintGCDetails
//        for (int i = 0; i < 5; i++) {
//            Byte[] b = new Byte[1024 * 1024];
//        }

        //测试进入老年的对象
        //参数:-Xmx1024m -Xms1024m -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintGCDetails
        //-XX:PrintHeapAtGC
        Map<Integer,byte[]> m = new HashMap<Integer, byte[]>();
        for(int i=0;i<5;i++){
            byte[] b = new byte[1024*1024];
            m.put(i,b);
        }

        for(int k=0;k<20;k++){
            for(int j=0;j<300;j++){
                byte[] b = new byte[1024*1024];

            }
        }



    }


}
