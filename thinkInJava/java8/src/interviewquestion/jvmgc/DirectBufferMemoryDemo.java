package interviewquestion.jvmgc;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * @author wcg
 * @CreateDate 2019/7/12-14:18
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("配置的MaxDirectMemory:"+ VM.maxDirectMemory()/(double)1024/1024 + "MB");

       Thread.sleep(3000);

        //-XX:MaxDirectMemorySize=5m  我们配置的5MB,但实际使用6MB,故意使坏
        ByteBuffer bb = ByteBuffer.allocateDirect(10*1024*1024);
    }
}
