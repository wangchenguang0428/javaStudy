package com.jvm.base001;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wcg
 * @CreateDate 2018/12/25-19:34
 */
public class Test006 {
    public static void main(String[] args) {
        //����:-Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000
        //���������ԭ����:����������������Ķ��� �����Ȱ����ݷ��䵽TLAB������,��˾�ʧȥ�������������Ļ���
        //����:-Xms30m -Xmx30m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:PretenureSizeThreshold=1000 -XX:-UseTLAB

        Map<Integer,byte[]> m = new HashMap<Integer, byte[]>();
        for(int i=0; i<5*1024; i++){
            byte[] b = new byte[1024];
            m.put(i,b);
        }

    }

}
