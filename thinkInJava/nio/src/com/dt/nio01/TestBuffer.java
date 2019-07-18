package com.dt.nio01;

import org.junit.Test;

import java.nio.ByteBuffer;

/*
 * һ����������Buffer������ Java NIO �и������ݵĴ�ȡ���������������顣���ڴ洢��ͬ�������͵�����
 *
 * �����������Ͳ�ͬ��boolean ���⣩���ṩ����Ӧ���͵Ļ�������
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * �����������Ĺ���ʽ����һ�£�ͨ�� allocate() ��ȡ������
 *
 * ������������ȡ���ݵ��������ķ�����
 * put() : �������ݵ���������
 * get() : ��ȡ�������е�����
 *
 * �����������е��ĸ��������ԣ�
 * capacity : ��������ʾ�����������洢���ݵ�������һ���������ܸı䡣
 * limit : ���ޣ���ʾ�������п��Բ������ݵĴ�С����limit �����ݲ��ܽ��ж�д��
 * position : λ�ã���ʾ�����������ڲ������ݵ�λ�á�
 *
 * mark : ��ǣ���ʾ��¼��ǰ position ��λ�á�����ͨ�� reset() �ָ��� mark ��λ��
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * �ġ�ֱ�ӻ��������ֱ�ӻ�������
 * ��ֱ�ӻ�������ͨ�� allocate() �������仺�������������������� JVM ���ڴ���
 * ֱ�ӻ�������ͨ�� allocateDirect() ��������ֱ�ӻ��������������������������ڴ��С��������Ч��
 */
public class TestBuffer {


    @Test
    public void test3() {

        //����ֱ�ӻ�����
        ByteBuffer buf = ByteBuffer.allocateDirect(1024);

        System.out.println(buf.isDirect());

    }


    @Test
    public void test2() {
        String str = "abcde";
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());

        byteBuffer.flip();


        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst,0,2);

        System.out.println(new String(dst,0,2));
        System.out.println(byteBuffer.position());

        //mark():���
        byteBuffer.mark();

        byteBuffer.get(dst,2,2);
        System.out.println(new String(dst,2,2));
        System.out.println(byteBuffer.position());

        //reset():�ָ���mark��λ��
        byteBuffer.reset();
        System.out.println(byteBuffer.position());

        //�жϻ��������Ƿ���ʣ�������
        if(byteBuffer.hasRemaining()) {
            //��ȡ�������п��Բ���������
            System.out.println(byteBuffer.remaining());
        }


    }


    @Test
    public void test1(){

        String str = "abcde";

        //1.����һ��ָ����С�Ļ�����
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);


        System.out.println("------------------allocate------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //2.����put()�������ݵ���������ȥ
        byteBuffer.put(str.getBytes());
        System.out.println("------------------put------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //3.�л��ɶ�ȡ���ݵ�ģʽ
        byteBuffer.flip();
        System.out.println("------------------flip------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //4.����get()��ȡ�������е�����
        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);
        System.out.println(new String(dst,0,dst.length));
        System.out.println("------------------get------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

      //5.rewind
        byteBuffer.rewind();
        System.out.println("------------------rewind------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //6.clear():��ջ�����,���ǻ������е�������Ȼ����,���Ǵ���"������"״̬
        byteBuffer.clear();
        System.out.println("------------------clear------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());


        System.out.println((char)byteBuffer.get(0));










    }
}
