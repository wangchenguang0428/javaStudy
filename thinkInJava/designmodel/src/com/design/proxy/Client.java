package com.design.proxy;

import com.design.proxy.interfaces.IGamePlayer;
import com.design.proxy.interfaces.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author wcg
 * @CreateDate 2019/6/26-18:13
 */
public class Client {

//    //ֱ�ӷ�����ʵ��ɫ
//    public static void main(String[] args) {
//        //����һ����Ϸ��ɫ
//        IGamePlayer player = new GamePlayer("����");
//        //��ʼ����Ϸ,����ʱ���
//        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//        player.login("zhangSan","password");
//        //��ʼɱ��
//        player.killBoss();
//        //����
//        player.upgrade();
//        //��¼��Ϸ����ʱ��
//        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//
//
//    }


    //ֱ�ӷ��ʴ�����
//    public static void main(String[] args) {
////        //����һ����Ϸ��ɫ
////        IGamePlayer player = new GamePlayer("����");
////
////        //Ȼ���ڶ���һ��������
////        IGamePlayer proxy = new GamePlayerProxy(player);
////
////
////        //��ʼ����Ϸ,����ʱ���
////        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                .format(new Date()));
////        proxy.login("zhangSan","password");
////        //��ʼɱ��
////        proxy.killBoss();
////        //����
////        proxy.upgrade();
////        //��¼��Ϸ����ʱ��
////        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                .format(new Date()));
////
////
////    }

    //ֱ�ӷ��ʴ�����
//    public static void main(String[] args) {
//        //����һ����Ϸ��ɫ
//        IGamePlayer player = new GamePlayer("����");
//
//        //Ȼ���ڶ���һ��������
//        IGamePlayer proxy = player.getProxy();
//
//
//        //��ʼ����Ϸ,����ʱ���
//        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//        proxy.login("zhangSan","password");
//        //��ʼɱ��
//        proxy.killBoss();
//        //����
//        proxy.upgrade();
//        //��¼��Ϸ����ʱ��
//        System.out.println("��ʼʱ��:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//
//
//    }

    //��̬����ĳ�����1
//    public static void main(String[] args) {
//        //����һ�����Ե����
//        IGamePlayer player = new GamePlayer("����");
//        //����һ��handler
//        InvocationHandler handler = new GamePlayIH(player);
//
//        //��ʼ����Ϸ,����ʱ���
//        System.out.println("��ʼʱ��:" + System.currentTimeMillis());
//        //������classLoader
//        ClassLoader cl = player.getClass().getClassLoader();
//        //��̬����һ��������
//        IGamePlayer proxy = (IGamePlayer) Proxy
//                .newProxyInstance(cl,new Class[]{IGamePlayer.class},handler);
//        //��¼
//        proxy.login("zhangsan","password");
//        //��ʼɱ��
//        proxy.killBoss();
//        //����
//        proxy.upgrade();
//        //��¼����ʱ��
//        System.out.println("����ʱ��:"+System.currentTimeMillis());
//
//
//    }

    //��̬��������2
    public static void main(String[] args) {
        //����һ������
        Subject subject = new RealSubject();
        //����һ��Handler
        InvocationHandler handler = new MyInvocationHandler(subject);
        //��������Ĵ���
        Subject proxy = DynamixProxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),handler);
        //�������Ϊ
        proxy.dosomething("finish");

    }

}
