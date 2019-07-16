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

//    //直接访问真实角色
//    public static void main(String[] args) {
//        //定义一个游戏角色
//        IGamePlayer player = new GamePlayer("张三");
//        //开始打游戏,记下时间戳
//        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//        player.login("zhangSan","password");
//        //开始杀怪
//        player.killBoss();
//        //升级
//        player.upgrade();
//        //记录游戏结束时间
//        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//
//
//    }


    //直接访问代理类
//    public static void main(String[] args) {
////        //定义一个游戏角色
////        IGamePlayer player = new GamePlayer("张三");
////
////        //然后在定义一个代练者
////        IGamePlayer proxy = new GamePlayerProxy(player);
////
////
////        //开始打游戏,记下时间戳
////        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                .format(new Date()));
////        proxy.login("zhangSan","password");
////        //开始杀怪
////        proxy.killBoss();
////        //升级
////        proxy.upgrade();
////        //记录游戏结束时间
////        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
////                .format(new Date()));
////
////
////    }

    //直接访问代理类
//    public static void main(String[] args) {
//        //定义一个游戏角色
//        IGamePlayer player = new GamePlayer("张三");
//
//        //然后在定义一个代练者
//        IGamePlayer proxy = player.getProxy();
//
//
//        //开始打游戏,记下时间戳
//        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//        proxy.login("zhangSan","password");
//        //开始杀怪
//        proxy.killBoss();
//        //升级
//        proxy.upgrade();
//        //记录游戏结束时间
//        System.out.println("开始时间:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//                .format(new Date()));
//
//
//    }

    //动态代理的场景类1
//    public static void main(String[] args) {
//        //定义一个痴迷的玩家
//        IGamePlayer player = new GamePlayer("张三");
//        //定义一个handler
//        InvocationHandler handler = new GamePlayIH(player);
//
//        //开始打游戏,记下时间戳
//        System.out.println("开始时间:" + System.currentTimeMillis());
//        //获得类的classLoader
//        ClassLoader cl = player.getClass().getClassLoader();
//        //动态产生一个代理者
//        IGamePlayer proxy = (IGamePlayer) Proxy
//                .newProxyInstance(cl,new Class[]{IGamePlayer.class},handler);
//        //登录
//        proxy.login("zhangsan","password");
//        //开始杀怪
//        proxy.killBoss();
//        //升级
//        proxy.upgrade();
//        //记录结束时间
//        System.out.println("结束时间:"+System.currentTimeMillis());
//
//
//    }

    //动态代理场景类2
    public static void main(String[] args) {
        //定义一个主题
        Subject subject = new RealSubject();
        //定义一个Handler
        InvocationHandler handler = new MyInvocationHandler(subject);
        //定义主题的代理
        Subject proxy = DynamixProxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),handler);
        //代理的行为
        proxy.dosomething("finish");

    }

}
