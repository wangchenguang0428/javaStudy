package com.atguigu.juc02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * һ������ִ���̵߳ķ�ʽ����ʵ�� Callable �ӿڡ� �����ʵ�� Runnable �ӿڵķ�ʽ�����������з���ֵ�����ҿ����׳��쳣��
 *
 * ����ִ�� Callable ��ʽ����Ҫ FutureTask ʵ�����֧�֣����ڽ�����������  FutureTask ��  Future �ӿڵ�ʵ����
 */
public class TestCallable {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        //1.ִ��Callable��ʽ,��ҪFutureTaskʵ�����֧��,���ڽ�����������
        FutureTask<Integer> result = new FutureTask<Integer>(threadDemo);
        new Thread(result).start();
        //2.�����߳������Ľ��
        try {
           Integer sum =  result.get();//FutrueTaskҲ�����ڱ���
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemo implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=0;i<100;i++){
            System.out.println(i);
            sum = sum+i;

        }
        return sum;
    }
}
