package interviewquestion.container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * û��ʵ���ķ�ŭ�ͱ�Թ��������
 * �����಻��ȫ����
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {

        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(
                    () -> {
                        map.put(UUID.randomUUID().toString().substring(0, 8), UUID.randomUUID().toString().substring(0, 8));
                        System.out.println(map);
                    }, String.valueOf(i)
            ).start();
        }


    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();   //Collections.synchronizedSet(new HashSet<>()); //new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(
                    () -> {
                        set.add(UUID.randomUUID().toString().substring(0, 8));
                        System.out.println(set);
                    }, String.valueOf(i)
            ).start();
        }

        new HashSet<>();
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(
                    () -> {
                        list.add(UUID.randomUUID().toString().substring(0, 8));
                        System.out.println(list);
                    }, String.valueOf(i)
            ).start();
        }


        /**
         * ��Ҫֻ�ǻ���,����ֻ��API���ù���ʦ
         * �ײ�ԭ��???
         *
         * 1.��������:java.util.ConcurrentModificationException
         *
         *
         * 2.����ԭ��
         *      �����޸���������,�ο����ǻ�����ǩ�����
         *      һ��������д,����һ��ͬѧ��������,�������ݲ�һ���쳣,�����޸��쳣
         *
         *
         *
         * 3.�������
         *   3.1 new Vector();
         *   3.2 Collections.synchronizedList(new ArrayList<>());
         *   3.3 new CopyOnWriteArrayList<>();
         *
         * 4.�Ż�����(ͬ���Ĵ��󲻷��ڶ���)
         *
         */}


}
