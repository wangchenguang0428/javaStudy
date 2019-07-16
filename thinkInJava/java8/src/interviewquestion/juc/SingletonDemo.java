package interviewquestion.juc;

/**
 * @author wcg
 * @CreateDate 2019/7/5-13:34
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " \t ���ǹ��췽��SingletonDemo()");

    }


    //DCL (Double check Lock˫�˼�������)
    public static synchronized SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        //���߳�(main�̵߳Ĳ�������......)
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());

        //�������߳��Ժ�,�����˺ܴ�ı仯
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }


    }
}
