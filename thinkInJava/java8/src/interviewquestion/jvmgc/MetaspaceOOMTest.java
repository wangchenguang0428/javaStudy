package interviewquestion.jvmgc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wcg
 * @CreateDate 2019/7/12-15:23
 * <p>
 * jvm����;
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * <p>
 * java8��֮��İ汾ʹ��Metaspace��������ô���
 * <p>
 * Metaspace�Ƿ�������HotSpot�е�ʵ��,����־ô�������������:Metaspace��������������ڴ��ж���
 * ʹ�ñ����ڴ�Ҳ����java8��,classes metadata(the virtual machines internal presentation of java class)
 * ,���洢�ڽ���Metaspace��native memory
 * <p>
 * ���ô�(java8��Ԫ�ռ�Metaspaceȡ����)�����������Ϣ:
 * <p>
 * ��������ص�����Ϣ
 * ������
 * ��̬����
 * ��ʱ�����Ĵ���
 * <p>
 * <p>
 * ģ��Metaspace�ռ����,���ǲ�����������Ԫ�ռ��,��ռ�ݵĿռ����ǻᳬ��Metaspaceָ���Ŀռ��С��
 */
public class MetaspaceOOMTest {


    static class OOMTest {

    }

    public static void main(String[] args) {
        int i = 0;//ģ��������ٴκ����쳣
        try {
            while (true) {
                i++;
                Enhancer enHancer = new Enhancer();
                enHancer.setSuperclass(OOMTest.class);
                enHancer.setUseCache(false);
                enHancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return proxy.invokeSuper(obj,args);
                    }
                });

                enHancer.create();
            }



        } catch (Throwable e) {
            System.out.println("***************���ٴκ������쳣:  " + i);
            e.printStackTrace();
        } finally {

        }


    }
}
