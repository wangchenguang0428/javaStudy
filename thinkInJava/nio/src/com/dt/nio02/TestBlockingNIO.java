package com.dt.nio02;


import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * һ��ʹ�� NIO �������ͨ�ŵ��������ģ�
 *
 * 1. ͨ����Channel������������
 *
 * 	   java.nio.channels.Channel �ӿڣ�
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. ��������Buffer�����������ݵĴ�ȡ
 *
 * 3. ѡ������Selector������ SelectableChannel �Ķ�·�����������ڼ�� SelectableChannel �� IO ״��
 *
 */
public class TestBlockingNIO {

    //�ͻ���

    @Test
    public void client() throws IOException {
        //1.��ȡͨ��
        SocketChannel socketChannel = SocketChannel
                .open(new InetSocketAddress("127.0.0.1",9898));

        FileChannel inChannel = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        //2.����ָ����С�Ļ�����
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //3.��ȡ�����ļ�,�����͵������
        while(inChannel.read(buf)!=-1){
            buf.flip();//�������л��ɶ�����ģʽ
            socketChannel.write(buf);
            buf.clear();
        }

        //4.�ر�ͨ��
        inChannel.close();
        socketChannel.close();

    }


    //�����
    @Test
    public void server() throws IOException {

        //1.��ȡͨ��
        ServerSocketChannel serverSocketChannel = ServerSocketChannel
                .open();
        FileChannel outChannel = FileChannel.open(Paths.get("5.txt"),
                StandardOpenOption.WRITE,StandardOpenOption.CREATE);


        //2.������
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //3.��ȡ�ͻ������ӵ�ͨ��
        SocketChannel socketChannel = serverSocketChannel.accept();

        //4.����ָ����С�Ļ�����
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //5.���տͻ��˵�����,�����浽����
        while(socketChannel.read(buf)!=-1){
            buf.flip();
            outChannel.write(buf);
            buf.clear();

        }

        //6.�رն�Ӧ��ͨ��
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();

    }
}
