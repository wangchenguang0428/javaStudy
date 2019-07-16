package interviewquestion.jvmgc;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author wcg
 * @CreateDate 2019/7/12-9:44
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("===============================");
        myWeakHahsMap();

    }

    private static void myWeakHahsMap() {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t "+map.size());



    }

    private static void myHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t "+map.size());




    }
}
