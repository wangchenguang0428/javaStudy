package com.jvm.base001;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcg
 * @CreateDate 2018/12/25-19:34
 */
public class Test006 {
    public static void main(String[] args) {
        //参数:-Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
        //这种现象的原因是:虚拟机对于体积不大的对象 会优先把数据分配到TLAB区域中,因此就失去了在老年代分配的机会
        //参数:-Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB

        Map<Integer,byte[]> m = new HashMap<Integer, byte[]>();
        for(int i=0; i<5*1024; i++){
            byte[] b = new byte[1024];
            m.put(i,b);
        }

    }

}
