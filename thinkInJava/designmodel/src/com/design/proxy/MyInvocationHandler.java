package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wcg
 * @CreateDate 2019/6/27-16:25
 */
public class MyInvocationHandler implements InvocationHandler {
    //������Ķ���
    private Object target = null;
    //ͨ�����캯������һ������
    public MyInvocationHandler(Object _obj){
        this.target = _obj;
    }

    //������
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //ִ�б�����ķ���
        return method.invoke(this.target,args);
    }
}
