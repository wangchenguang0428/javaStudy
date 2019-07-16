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
        //Ѱ��JoinPoint���ӵ�,aop���ʹ��Ԫ���ݶ���
        if(true){
            //ִ��һ��ǰ��֪ͨ
            new BeforeAdvice().exec();
        }
        return (T)Proxy.newProxyInstance(loader,interfaces,h);

    }
}
