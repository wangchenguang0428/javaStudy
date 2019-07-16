package com.design.proxy;

import com.design.proxy.interfaces.Subject;

/**
 * @author wcg
 * @CreateDate 2019/6/27-16:17
 */
public class RealSubject implements Subject {
    @Override
    public void dosomething(String str) {
        System.out.println("do something!--->"+str);
    }
}
