package com.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wcg
 * @CreateDate 2019/6/26-18:52
 */
public class GamePlayIH implements InvocationHandler {
    //��������
    Class cls = null;
    //�������ʵ��
    Object obj = null;

    //��Ҫ����˭
    public GamePlayIH(Object _obj) {
        this.obj = _obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if(method.getName().equalsIgnoreCase("login")){
            System.out.println("��¼�˺�֮ǰ");
        }
        Object result = method.invoke(this.obj, args);
        //����ǵ�¼����,������Ϣ
        if(method.getName().equalsIgnoreCase("login")){
            System.out.println("���������ҵ��˺ŵ�¼");
        }
        return result;
    }
}
