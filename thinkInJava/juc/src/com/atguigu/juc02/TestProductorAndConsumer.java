package com.atguigu.juc02;

/*
 * �����ߺ������߰���
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producter pro =new Producter(clerk);
        Consumer consumer = new Consumer(clerk);
        new Thread(pro,"������A").start();
        new Thread(consumer,"������B").start();
        new Thread(pro,"������C").start();
        new Thread(consumer,"������D").start();


    }
}

/*
class Clerk{

    private int product = 0;


    //����
    public synchronized  void get(){
        while(product>=1){
            System.out.println("��Ʒ����");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+ " : "+ ++product);
            this.notifyAll();

    }

    //����
    public synchronized void sale(){

        while(product<=0){
            System.out.println("ȱ��!");
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
*/