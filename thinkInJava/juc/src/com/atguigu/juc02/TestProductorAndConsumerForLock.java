package com.atguigu.juc02;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wcg
 * @CreateDate 2019/6/8-13:50
 */
public class TestProductorAndConsumerForLock {



}

class Clerk{

    private int product = 0;
    Lock lock = new ReentrantLock();


    //进货
    public   void get(){
        while(product>=1){
            System.out.println("产品已满");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+ " : "+ ++product);
        this.notifyAll();

    }

    //卖货
    public synchronized void sale(){

        while(product<=0){
            System.out.println("缺货!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName()+ " : "+ --product);
        this.notifyAll();


    }

}

class Producter implements Runnable{
    private Clerk clerk;

    public Producter(Clerk clerk){
        this.clerk = clerk;
    }


    @Override
    public void run() {
        for(int i=0; i<30; i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }

    }
}

class Consumer implements Runnable{
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run(){
        for(int i=0; i<30; i++){
            clerk.sale();
        }
    }

}