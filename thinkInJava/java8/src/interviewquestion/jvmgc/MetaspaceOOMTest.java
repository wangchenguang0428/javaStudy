package interviewquestion.jvmgc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wcg
 * @CreateDate 2019/7/12-15:23
 * <p>
 * jvm参数;
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 * <p>
 * java8及之后的版本使用Metaspace来替代永久代。
 * <p>
 * Metaspace是方法区在HotSpot中的实现,它与持久代最大的区别在于:Metaspace并不是在虚拟机内存中而是
 * 使用本地内存也即在java8中,classes metadata(the virtual machines internal presentation of java class)
 * ,被存储在叫做Metaspace的native memory
 * <p>
 * 永久代(java8后被元空间Metaspace取代了)存放了以下信息:
 * <p>
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * <p>
 * <p>
 * 模拟Metaspace空间溢出,我们不断生成类往元空间灌,类占据的空间总是会超过Metaspace指定的空间大小的
 */
public class MetaspaceOOMTest {


    static class OOMTest {

    }

    public static void main(String[] args) {
        int i = 0;//模拟计数多少次后发生异常
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
            System.out.println("***************多少次后发生了异常:  " + i);
            e.printStackTrace();
        } finally {

        }


    }
}
