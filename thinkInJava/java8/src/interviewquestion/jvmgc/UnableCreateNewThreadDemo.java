package interviewquestion.jvmgc;

/**
 * @author wcg
 * @CreateDate 2019/7/12-14:54
 *高并发请求服务器的时候,经常出现如下异常:Java.lang.OutofMemoryError:unable to create new native thread
 *准确的讲native thread 异常与对应的平台有关
 *
 * 导致原因:
 * 1.  你得应用创建了太多线程了,一个应用进程创建多个线程,超过系统承载极限
 * 2.  你得服务器并不允许你的应用程序创建这么多线程,linux系统默认单个进程可以创建的线程数是1024个
 *     你得应用创建超过这个数量,就会报Java.lang.OutofMemoryError:unable to create new native thread
 *
 * 解决办法:
 * 1.想办法降低你应用程序创建线程的数量,分析应用是否真的需要创建这么多线程,如果不是,改代码将线程降到最低
 * 2.对于有的应用,确实需要创建很多线程,远超过linux系统的默认1024个线程的限制,可以通过修改linux服务器的配置,扩大linu默认限制
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
