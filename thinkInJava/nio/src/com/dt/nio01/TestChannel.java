package com.dt.nio01;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/*
 * һ��ͨ����Channel��������Դ�ڵ���Ŀ��ڵ�����ӡ��� Java NIO �и��𻺳��������ݵĴ��䡣Channel �����洢���ݣ������Ҫ��ϻ��������д��䡣
 *
 * ����ͨ������Ҫʵ����
 * 	java.nio.channels.Channel �ӿڣ�
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * ������ȡͨ��
 * 1. Java ���֧��ͨ�������ṩ�� getChannel() ����
 * 		���� IO��
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		����IO��
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. �� JDK 1.7 �е� NIO.2 ��Ը���ͨ���ṩ�˾�̬���� open()
 * 3. �� JDK 1.7 �е� NIO.2 �� Files ������� newByteChannel()
 *
 * �ġ�ͨ��֮������ݴ���
 * transferFrom()
 * transferTo()
 *
 * �塢��ɢ(Scatter)��ۼ�(Gather)
 * ��ɢ��ȡ��Scattering Reads������ͨ���е����ݷ�ɢ�������������
 * �ۼ�д�루Gathering Writes����������������е����ݾۼ���ͨ����
 *
 * �����ַ�����Charset
 * ���룺�ַ��� -> �ֽ�����
 * ���룺�ֽ�����  -> �ַ���
 *
 */
public class TestChannel {


    @Test
    public void test6() throws Exception {
        Charset cs1 = Charset.forName("GBK");

        //��ȡ�������ͽ�����
        CharsetEncoder ce = cs1.newEncoder();

        //��ȡ������
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("datang����");
        cBuf.flip();
        //����
        ByteBuffer bBuf = ce.encode(cBuf);

        for (int i = 0; i < 10; i++) {
            System.out.println(bBuf.get());
        }
        //����
        bBuf.flip();
        System.out.println(bBuf.limit());
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());

        System.out.println("-------------------------");
        bBuf.flip();
        Charset cs2 = Charset.forName("GBK");
        CharBuffer cBuf3 = cs2.decode(bBuf);
        System.out.println(cBuf3.toString());


    }


    //�ַ���
    @Test
    public void test5() {
        Map<String, Charset> map = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> entries = map.entrySet();
        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "======" + entry.getValue());
        }

    }


    @Test
    public void test4() throws Exception {
        RandomAccessFile raf1 = new RandomAccessFile("1.txt", "rw");

        //1.��ȡͨ��
        FileChannel channel1 = raf1.getChannel();

        //2.����ָ����С�Ļ�����
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        //3.��ɢ��ȡ
        ByteBuffer[] bufs = {buf1, buf2};
        channel1.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("----------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        //4.�ۼ�д��
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);


    }


    //ͨ��֮������ݴ���(ֱ�ӻ�����)
    @Test
    public void test3() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("2.pdf"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.pdf"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

//        inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();

    }


    //2.ʹ��ֱ�ӻ���������ļ��ĸ���(�ڴ�ӳ���ļ�)  ֱ�ӻ����ļ�ֻ��ByteBuffer֧��

    @Test
    public void test2() throws IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("2.pdf"),
                StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.pdf"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW, StandardOpenOption.READ);

        //�ڴ�ӳ���ļ�
        MappedByteBuffer inMapBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0,
                inChannel.size());
        MappedByteBuffer outMapBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0,
                inChannel.size());

        //ֱ�ӶԻ������������ݵĶ�д����
        byte[] dst = new byte[inMapBuf.limit()];
        inMapBuf.get(dst);
        outMapBuf.put(dst);

        inChannel.close();
        outChannel.close();


    }


    //1.����ͨ������ļ��ĸ���
    @Test
    public void test1() throws Exception {
        FileInputStream fis = new FileInputStream("1.pdf");
        FileOutputStream fos = new FileOutputStream("2.pdf");

        //�ٻ�ȡͨ��
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //�ڷ���ָ����С�Ļ�����
        ByteBuffer buf = ByteBuffer.allocate(1024);


        ////�۽�ͨ���е����ݴ��뻺������
        while (inChannel.read(buf) != -1) {
            buf.flip();//�л��ɶ�ȡ���ݵ�ģʽ
            //�ܽ��������е�����д��ͨ��
            outChannel.write(buf);
            buf.clear();//��ջ�����

        }


        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();


    }
}
