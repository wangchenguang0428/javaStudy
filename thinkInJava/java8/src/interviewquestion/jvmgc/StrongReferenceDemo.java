package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/11-17:58
 * ֻҪ��һ������ָ��ö��� �����Ǳ���OOM��Ҳ���ᱻ����
 *
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();//���������Ĭ�Ͼ���ǿ����
        Object obj2 = obj1;
        obj1 = null;//�ÿ�
        System.gc();
        System.out.println(obj2);
    }

}
