package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/12-14:54
 *�߲��������������ʱ��,�������������쳣:Java.lang.OutofMemoryError:unable to create new native thread
 *׼ȷ�Ľ�native thread �쳣���Ӧ��ƽ̨�й�
 *
 * ����ԭ��:
 * 1.  ���Ӧ�ô�����̫���߳���,һ��Ӧ�ý��̴�������߳�,����ϵͳ���ؼ���
 * 2.  ��÷����������������Ӧ�ó��򴴽���ô���߳�,linuxϵͳĬ�ϵ������̿��Դ������߳�����1024��
 *     ���Ӧ�ô��������������,�ͻᱨJava.lang.OutofMemoryError:unable to create new native thread
 *
 * ����취:
 * 1.��취������Ӧ�ó��򴴽��̵߳�����,����Ӧ���Ƿ������Ҫ������ô���߳�,�������,�Ĵ��뽫�߳̽������
 * 2.�����е�Ӧ��,ȷʵ��Ҫ�����ܶ��߳�,Զ����linuxϵͳ��Ĭ��1024���̵߳�����,����ͨ���޸�linux������������,����linuĬ������
 *
 *
 */
public class UnableCreateNewThreadDemo {

    public static void main(String[] args) {
        for(int i=1; ;i++) {
            System.out.println("************** i=" + i);

            new Thread(()->{
                try{
                    Thread.sleep(Integer.MAX_VALUE);
                }catch (Exception e){
                      e.printStackTrace();
                }finally {

                }

            },""+i).start();
        }
    }

}
