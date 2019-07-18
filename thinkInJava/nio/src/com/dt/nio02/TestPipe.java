package com.dt.nio02;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * @author wcg
 * @CreateDate 2019/7/18-11:25
 */
public class TestPipe {

    @Test
    public void test1 () throws IOException {
        //1.获取管道
        Pipe pipe = Pipe.open();

        //线程1向管道发送数据
        new Thread(()->{
            //2.将缓冲区中的数据写入管道
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Pipe.SinkChannel sinkChannel = pipe.sink();
            buf.put("通过单向管道发送数据".getBytes());
            buf.flip();
            try {
                sinkChannel.write(buf);
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"1").start();

        //线程2接收管道数据
        new Thread(()->{
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //3. 读取缓冲区中的数据
            Pipe.SourceChannel sourceChannel = pipe.source();

            int len = 0;
            try {
                len = sourceChannel.read(buf);
                buf.flip();
                System.out.println(new String(buf.array(), 0, len));

                sourceChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        },"2").start();







    }

}
