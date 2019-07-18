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
        //1.��ȡ�ܵ�
        Pipe pipe = Pipe.open();

        //�߳�1��ܵ���������
        new Thread(()->{
            //2.���������е�����д��ܵ�
            ByteBuffer buf = ByteBuffer.allocate(1024);
            Pipe.SinkChannel sinkChannel = pipe.sink();
            buf.put("ͨ������ܵ���������".getBytes());
            buf.flip();
            try {
                sinkChannel.write(buf);
                sinkChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        },"1").start();

        //�߳�2���չܵ�����
        new Thread(()->{
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //3. ��ȡ�������е�����
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
