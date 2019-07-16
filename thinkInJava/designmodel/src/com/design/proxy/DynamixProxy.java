package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wcg
 * @CreateDate 2019/6/28-9:35
 */
public class DynamixProxy <T>{
    public static <T> T newProxyInstance(ClassLoader loader
            , Class<?>[] interfaces, InvocationHandler h){
        //寻找JoinPoint连接点,aop框架使用元数据定义
        if(true){
            //执行一个前置通知
            new BeforeAdvice().exec();
        }
        return (T)Proxy.newProxyInstance(loader,interfaces,h);

    }
}
