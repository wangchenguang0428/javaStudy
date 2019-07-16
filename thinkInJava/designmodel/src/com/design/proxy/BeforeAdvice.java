package com.design.proxy;

import com.design.proxy.interfaces.IAdvice;

/**
 * @author wcg
 * @CreateDate 2019/6/28-9:42
 */
public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("我是前置通知,我被执行了");
    }
}
