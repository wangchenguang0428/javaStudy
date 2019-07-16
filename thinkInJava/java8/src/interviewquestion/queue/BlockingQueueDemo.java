package interviewquestion.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue:��һ������������н���������,�˶��а�(FIFO)ԭ���������
 * LinkedBlockingQueue:һ����������ṹ����������,�˶��а�(FIFO)����Ԫ��,������ͨ������ArrayBlockingQueue
 * SynchronousQueue:һ�����洢Ԫ�ص��������С�ÿ�������������ȵ���һ���̵߳����߳��Ƴ�����������������һֱ��������״̬������ͨ��Ҫ����LinkedBlockingQueue �� ArrayBlockingQueue
 *
 * @author wcg
 * @CreateDate 2019/7/8-21:57
 * <p>
 * <p>
 * 1.����
 * <p>
 * <p>
 * 2.��������
 * 2.1 ����������û�кõ�һ��
 * <p>
 * 2.2 ���ò�����,��ι���
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {
//        List list = new ArrayList();

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        try{
//            System.out.println(blockingQueue.add("a"));
//            System.out.println(blockingQueue.add("b"));
//            System.out.println(blockingQueue.add("c"));
//
//            System.out.println(blockingQueue.element());
//
//            System.out.println(blockingQueue.remove());
//            System.out.println(blockingQueue.remove());
//            System.out.println(blockingQueue.remove());

//            System.out.println(blockingQueue.offer("a"));
//            System.out.println(blockingQueue.offer("b"));
//            System.out.println(blockingQueue.offer("c"));
//            System.out.println(blockingQueue.offer("x"));
//
//            System.out.println(blockingQueue.peek());
//
//            System.out.println(blockingQueue.poll());
//            System.out.println(blockingQueue.poll());
//            System.out.println(blockingQueue.poll());
//            System.out.println(blockingQueue.poll());
//
//            blockingQueue.put("a");
//            blockingQueue.put("a");
//            blockingQueue.put("b");
//            System.out.println("===============================");
//            blockingQueue.put("c");

//            System.out.println(blockingQueue.take());
//            System.out.println(blockingQueue.take());
//            System.out.println(blockingQueue.take());
//            System.out.println(blockingQueue.take());

//            blockingQueue.take();


            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
            System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));


        }catch (Exception e){
              e.printStackTrace();
        }




    }
}
