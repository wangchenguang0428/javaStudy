package com.atguigu.juc01;

/**
 * @author wcg
 * @CreateDate 2019/5/29-14:40
 *  һ��volatile �ؼ��֣�������߳̽��в�����������ʱ�����Ա�֤�ڴ��е����ݿɼ���
 *  * 					  ����� synchronized ��һ�ֽ�Ϊ��������ͬ�����ԡ�
 *  *
 *  * ע�⣺
 *  * 1. volatile ���߱��������ԡ�
 *  * 2. volatile ���ܱ�֤�����ġ�ԭ���ԡ�
 */
public class TestVolatile {
    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();
        new Thread(td).start();

        while(true){
//            synchronized (td) {
                if (td.isFlag()) {
                    System.out.println("---------------------");
                    break;
                }
//            }
        }
    }
}


class ThreadDemo implements Runnable {


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    volatile private  boolean flag = false;


    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println(" flag= " + isFlag());

    }
}
