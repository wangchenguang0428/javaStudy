package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/11-13:07
 * ��java��,������ΪgGC Roots�Ķ�����
 *
 * 1.�����ջ(ջ֡�еı��ر�����)�����õĶ���
 * 2.�������еľ�̬�������õĶ���
 * 3.�������г������õĶ���
 * 4.���ط���ջ��JNI(��һ��˵��native����)�����õĶ���
 *
 */
public class GCRootDemo {

    private byte[] byteArray = new byte[100*1024*1024];

//    private static GCRootDemo2 t2;
//    private static final GCRootDemo3 t3 = new GCRootDemo(8);



    public static void  m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("��һ��gc���");

    }

    public static void main(String[] args) {
        m1();
    }
}
